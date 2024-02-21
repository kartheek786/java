package jdbc; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnection_with_try {
    public static void main(String[] args) {
        Connection con = null;

        try {
            // Register the Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Create a connection to the database
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SYSTEM", "system", "Ckarthik1");

            if (con != null) {
                System.out.println("Connected to the Oracle database.");
                // Perform database operations here
            } else {
                System.out.println("Failed to connect to the Oracle database.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Oracle JDBC driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Failed to connect to the Oracle database.");
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

