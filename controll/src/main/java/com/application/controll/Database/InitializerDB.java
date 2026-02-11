/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.controll.Database;

import java.sql.Connection;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ruan
 */
public class InitializerDB {
    
    public static void createTables() throws SQLException {
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
        
        /// Instance DB-Connection
        ConnectionDB db = new ConnectionDB();
        
        Connection conn = db.getConnectionDB();
        Statement stmt = conn.createStatement();
        
        try {
            
           stmt.execute(sql);
           System.out.println("Tables checked/created successfully!");
            
        } catch (SQLException exception) {
            System.err.println("Cannot Create Table Because => " + exception.getMessage());
        }
    }
}
