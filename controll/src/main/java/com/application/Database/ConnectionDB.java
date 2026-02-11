/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.Database;

import com.application.controll.core.Default;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ruan
 */
public class ConnectionDB implements Default {
    private static final String url = "jdbc:sqlite:controllDB.db";
    private static Connection conn  = null;
    
    @Override
    public Connection getConnectionDB() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url);
                System.out.println("Sqlite Connection Established!");
            }
        } catch (SQLException exception) {
            System.err.println("Sqlite DB Cannot Connection => " + exception.getMessage());
        }
        
        return conn;
    }
    
    private static void createTables() {
        String sql = """
                     CREATE TABLE IF NOT EXISTS Products (
                        productId                   TEXT PRIMARY KEY NOT NULL,
                        productCode                 TEXT UNIQUE,
                        productName                 TEXT NOT NULL,
                        productDescription          TEXT,
                        productBasePrice            NUMERIC(9,2) NOT NULL,
                        productPrice                NUMERIC(9,2) NOT NULL,
                        productAcquisitionPrice     NUMERIC(9,2) NOT NULL,
                        productTAX                  NUMERIC(9,2),
                        productAmount               INTEGER NOT NULL,
                        productCategory             TEXT NOT NULL,
                        productIsActive             INTEGER
                     );
                     """;
    }
}
