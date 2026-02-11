/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.controll.Service;

import com.application.Database.ConnectionDB;
import com.application.controll.Model.ProductModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ruan
 */
public class ProductService {
    private static Connection conn =  new ConnectionDB().getConnectionDB();
    
    /// Example Insert with UUID
    /// pst.setString(1, product.getProductId().toString()));
    ///
    /// Example Read with UUID
    /// Convert String to UUID
    /// String idStr = rs.getString("productId");
    /// pst.setProductId(UUID.fromString(idStr));
    
    public ProductModel insertProduct() throws SQLException {
        
        try {
            PreparedStatement pst = conn.prepareStatement("");
            
            pst.setString(1, "columnName");
            pst.executeUpdate();
        } catch (SQLException exception) {
            System.err.println("Sqlite DB Cannot Connection => " + exception.getMessage());
        } finally {
            conn.close();
        }
        
        return null;
    }
}
