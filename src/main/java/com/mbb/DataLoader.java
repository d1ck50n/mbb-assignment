package com.mbb;

import java.io.*;
import java.math.BigDecimal;
import java.sql.*;

public class DataLoader {

    private static final String JDBC_URL = "jdbc:h2:file:./data/maybank";
    private static final String FILE_PATH = "/dataSource.txt";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, "sa", "")) {
            createTable(connection);
            loadData(FILE_PATH, connection);
        } catch (SQLException e) {
            System.err.println("OOps... Something went wrong: " + e.getMessage());
        }
    }

    private static void createTable(Connection connection) {
        String sql = "CREATE TABLE IF NOT EXISTS transactions ("
                + "id INTEGER PRIMARY KEY AUTO_INCREMENT, "
                + "account_number BIGINT NOT NULL, "
                + "trx_amount DECIMAL(15, 2) NOT NULL, "
                + "description VARCHAR(255) NOT NULL, "
                + "trx_date DATE NOT NULL, "
                + "trx_time TIME NOT NULL, "
                + "customer_id INT NOT NULL);";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    private static void loadData(String filePath, Connection connection) {
        String insertSQL = "INSERT INTO transactions ("
                + "account_number, trx_amount, description, trx_date, trx_time, customer_id) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (InputStream inputStream = DataLoader.class.getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new FileNotFoundException("File not found: " + filePath);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                 PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {

                String line;
                br.readLine(); // Skip the header
                while ((line = br.readLine()) != null) {
                    String[] values = line.split("\\|");
                    if (values.length < 6) {
                        System.err.println("Invalid line: " + line);
                        continue; // Skip invalid lines
                    }
                    pstmt.setLong(1, Long.parseLong(values[0]));
                    pstmt.setBigDecimal(2, new BigDecimal(values[1]));
                    pstmt.setString(3, values[2]);
                    pstmt.setDate(4, Date.valueOf(values[3]));
                    pstmt.setTime(5, Time.valueOf(values[4]));
                    pstmt.setInt(6, Integer.parseInt(values[5]));
                    pstmt.addBatch();
                }
                pstmt.executeBatch(); // Execute batch insert
                System.out.println("Data loaded successfully.");
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error inserting data: " + e.getMessage());
        }
    }
}
