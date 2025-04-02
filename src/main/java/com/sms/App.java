package com.sms;

import oracle.jdbc.OracleConnectionBuilder;
import oracle.jdbc.OracleDriver;
import oracle.jdbc.datasource.OracleDataSource;

import javax.sql.PooledConnection;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
//        try {
////            Getall();
//            Connection conn = Conn();
//            for (int i = 0; i < 39000; i++) {
//                StringBuilder sb = new StringBuilder("INSERT INTO PRODUCTS_BACKUP (PRODUCT_NAME, DESCRIPTION, STANDARD_COST, LIST_PRICE, CATEGORY_ID) VALUES ('");
//                sb.append("TEST"+i);
//                sb.append("', 'TEST PRODUCT', '10', '10', '5')");
//                Insert(String.valueOf(sb), conn);
//            }
//            conn.close();
////            Delete("TEST0");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        IndertMany();
    }

    private static void Getall() throws SQLException {
        Connection conn = Conn();
        Statement stmt = conn.createStatement();
        String query = "SELECT * FROM products ORDER BY PRODUCT_ID ASC";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            int id = rs.getInt("PRODUCT_ID");
            System.out.println(id);
        }
        ConnClose(conn);
    }

    private static Connection Conn() {
        String jdbcUrl = "jdbc:oracle:thin:@//localhost:1521/eis";  // Example JDBC URL
        Properties connectionProperties = new Properties();
        connectionProperties.setProperty("user", "dbuser");
        connectionProperties.setProperty("password", "dbuser");

        Connection conn = null;
        try {
            OracleDriver oracleConnector = new OracleDriver();
            conn = oracleConnector.connect(jdbcUrl, connectionProperties);
            System.out.println("Connection established: " + (conn != null));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    private static void ConnClose(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String Insert(String query, Connection conn) throws SQLException {
//        Connection conn = Conn();
        Statement stmt = conn.createStatement();
        int rs = stmt.executeUpdate(query);
//        ConnClose(conn);
        return "Rows Affected : " + rs;
    }

    private static String Delete(String name) throws SQLException {
        Connection conn = Conn();
        Statement stmt = conn.createStatement();
        String query = "DELETE products WHERE PRODUCT_NAME = '" + name + "'";
        int rs = stmt.executeUpdate(query);
        ConnClose(conn);
        return "Rows Affected : " + rs;
    }

    private static void IndertMany() {
        try {
            // Get the connection
            Connection conn = Conn();

            // Disable auto-commit for batch processing
            conn.setAutoCommit(false);

            // Prepare the SQL statement
            String insertSQL = "INSERT INTO PRODUCTS_BACKUP (PRODUCT_NAME, DESCRIPTION, STANDARD_COST, LIST_PRICE, CATEGORY_ID) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);


            for (int i = 0; i < 1000000; i++) {
                // Start timing the insertion
                long startTime = System.currentTimeMillis();  // Record start time
                // Set values for each parameter in the batch
                pstmt.setString(1, "TEST" + i);  // PRODUCT_NAME
                pstmt.setString(2, "TEST PRODUCT");  // DESCRIPTION
                pstmt.setDouble(3, 10.00);  // STANDARD_COST
                pstmt.setDouble(4, 10.00);  // LIST_PRICE
                pstmt.setInt(5, 5);  // CATEGORY_ID

                // Add the current insert statement to the batch
                pstmt.addBatch();

                // Execute the batch every 1000 records to avoid excessive memory usage
                if (i % 100000 == 0 || i == 1000000 - 1) {
                    pstmt.executeBatch();
                    conn.commit(); // Commit the batch after every 10000 inserts

                    // End timing the insertion
                    long endTime = System.currentTimeMillis();  // Record end time

                    // Calculate the duration in seconds
                    long duration = (endTime - startTime) / 1000;
                    System.out.println("Time taken to insert 100.000 rows: " + duration + " seconds");
                }
            }

            // Close resources
            pstmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
