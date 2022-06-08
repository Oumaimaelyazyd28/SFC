package dao;

import java.sql.*;

public class ConnectDB {

    public void Connect(){
        final String DB_URL = "jdbc:mysql://localhost/sfc?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";
        Connection conn;
        PreparedStatement pst;

        try {
            conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt = conn.createStatement();
            System.out.println("valider!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
