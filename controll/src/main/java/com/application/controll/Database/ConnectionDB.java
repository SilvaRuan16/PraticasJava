/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.controll.Database;

import com.application.controll.core.Default;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ruan
 */
public class ConnectionDB implements Default {
    
    @Override
    public Connection getConnectionDB() {
        String url = "jdbc:sqlite:controllDB.db";
        Connection conn = null;
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url);
                System.out.println("Sqlite Connection Established!");
                
                InitializerDB.createTables();
            }
        } catch (SQLException exception) {
            System.err.println("Sqlite DB Cannot Connection => " + exception.getMessage());
        }
        
        return conn;
    }
}
