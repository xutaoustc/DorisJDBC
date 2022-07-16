package com.testDoris;

import java.security.PrivilegedExceptionAction;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://10.30.66.30:10018", "root", "");
        Statement st = conn.createStatement();
        st.setMaxRows(100);
        ResultSet res = st.executeQuery("show databases");
        while (res.next()) {
            System.out.println(res.getString(1));
        }
        res.close();
        st.close();
        conn.close();
    }
}
