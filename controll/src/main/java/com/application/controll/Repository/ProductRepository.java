/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.controll.Repository;

import com.application.controll.Database.ConnectionDB;
import com.application.controll.Model.ProductModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ruan
 */
public class ProductRepository {

    /// Example Insert with UUID
    /// pst.setString(1, product.getProductId().toString()));
    ///
    /// Example Read with UUID
    /// Convert String to UUID
    /// String idStr = rs.getString("productId");
    /// pst.setProductId(UUID.fromString(idStr));

    private final ConnectionDB db = new ConnectionDB();
    private final Connection conn = db.getConnectionDB();

    public boolean existsByCode(String code) {
        String sql = """
                     SELECT COUNT(*) FROM Products
                     WHERE productCode = ?
                     """;

        try (PreparedStatement pst = conn.prepareStatement(sql);) {
            pst.setString(1, code);
            try (ResultSet rs = pst.executeQuery();) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException exception) {
            System.err.println("Cannot Check If Exists Data Because => " + exception.getMessage());
        }

        return false;
    }

    public void save(ProductModel product) {

        String sql = """
                     INSERT INTO Products 
                     (productId, productCode, productName, productDescription, productBasePrice, productPrice, productAcquisitionPrice, productTAX, productAmount, productCategory, productIsActive)
                     VALUES 
                     (?,?,?,?,?,?,?,?,?,?,?);
                     """;

        try (PreparedStatement pst = conn.prepareStatement(sql);) {
            pst.setString(1, product.getProductId().toString());
            pst.setString(2, product.getProductCode());
            pst.setString(3, product.getProductName());
            pst.setString(4, product.getProductDescription());
            pst.setDouble(5, product.getProductBasePrice());
            pst.setDouble(6, product.getProductPrice());
            pst.setDouble(7, product.getProductAcquisitionPrice());
            pst.setDouble(8, product.getProductTAX());
            pst.setInt(9, product.getProductAmount());
            pst.setString(10, product.getProductCategory());
            /// Convert Boolean to Integer (SQLite standard)
            pst.setString(11, product.getProductIsActive());

            pst.executeUpdate();
            System.out.println("Product Persistented Successfully => " + product.getProductName());

        } catch (SQLException exception) {
            System.err.println("Cannot Save Data Because => " + exception.getMessage());
        }
    }

    public List<ProductModel> findAll() {
        List<ProductModel> list = new ArrayList<>();
        String sql = """
                     SELECT productCode, 
                     productName, 
                     productDescription, 
                     productBasePrice, 
                     productPrice
                     FROM Products
                     """;

        try (PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                ProductModel product = new ProductModel(rs.getString("productCode"), rs.getString("productName"), rs.getString("productDescription"), rs.getDouble("productBasePrice"), rs.getDouble("productPrice"));
                list.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Cannot read the table info => " + e.getMessage());
        }
        
        return list;
    }
}
