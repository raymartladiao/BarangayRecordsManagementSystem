package com.mycompany.barangayrecords;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DB {

    private static final String URL =
            "jdbc:mysql://localhost:3306/barangay_records"
            + "?useSSL=false&serverTimezone=Asia/Manila&allowPublicKeyRetrieval=true";

    // Change these to match your own MySQL account.
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Raymart112006@";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
    }

    public static void showConnectionError(Exception ex) {
        JOptionPane.showMessageDialog(
                null,
                "Could not connect to MySQL. Check that MySQL Server is running, "
                + "the database exists, and DB_USER / DB_PASSWORD are correct.\n\n"
                + ex.getMessage(),
                "Database Connection Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
