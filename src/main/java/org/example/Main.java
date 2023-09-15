package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL_PATTERN = "jdbc:mysql://%s:%d/%s?rewriteBatchedStatements=true";
    private static final int PORT = 9030;   // query_port of Leader Node

    public static void main(String[] args) throws Exception {
        String jdbcAddress = args[0];
        String db = args[1];
        String table = args[2];
        String user = args[3];
        String passwd = args[4];

        String dbUrl = String.format(DB_URL_PATTERN, jdbcAddress, PORT, db);

        Class.forName(JDBC_DRIVER);
        Connection conn = DriverManager.getConnection(dbUrl, user, passwd);

        //Query
        Statement stmtQuery = conn.createStatement();
        ResultSet resultSet = stmtQuery.executeQuery( String.format("select count(1) from %s", table) );

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }
    }
}