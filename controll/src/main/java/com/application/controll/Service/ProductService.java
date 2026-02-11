/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.controll.Service;

import com.application.controll.Database.ConnectionDB;
import com.application.controll.Model.ProductModel;
import com.application.controll.Repository.ProductRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ruan
 */
public class ProductService {
    private static final ProductRepository repository = new ProductRepository();
    
    public Double calculateAdjustment(ProductModel product, boolean useBasePrice) {
        Double salePrice = useBasePrice 
                           ? product.getProductBasePrice() 
                           : product.getProductPrice();
        Double taxRate = product.getProductTAX() / 100;
        Double taxAmount = salePrice * taxRate;
        return salePrice - taxAmount - product.getProductAcquisitionPrice();
    }
    
    public boolean saveProduct(ProductModel product) throws Exception {
        
        if (product.getProductTAX() == null            || 
            product.getProductTAX().toString().isEmpty()) {
            product.setProductTAX(0.0);
        }
        
        if (product.getProductPrice()             == null ||
            product.getProductBasePrice()         == null ||
            product.getProductAcquisitionPrice()  == null  ) {
            System.err.println("Error => The Price Cannot Be Negative.");
            return false;
        }
        
        if (product.getProductPrice()       < 0    || 
            product.getProductBasePrice()   < 0    || 
            product.getProductAcquisitionPrice() < 0) {
            System.err.println("Error => The Price Cannot Be Negative.");
            return false;
        }
        
        if (product.getProductName() == null ||
            product.getProductName().trim().isEmpty()) {
            System.err.println("Error => The Name Cannot Be Null/Empty");
            return false;
        }
        
        if (repository.existsByCode(product.getProductCode())) {
            System.err.println("Error => Already Exists Product With This Code: " + product.getProductCode());
            return false;
        }
        
        if (product.getProductAmount() < 3) {
            System.err.println("Warning: Low stock alert (minimum 3 units recommended).");
        }
        
        if (product.getProductPrice() < product.getProductBasePrice()) {
            System.err.println("The Product Price Cannot Be Lower Than Product Price Base.");
            return false;
        }
        
        if (product.getProductBasePrice() < product.getProductAcquisitionPrice()) {
            System.err.println("The Product Base Price Cannot Be Lower Than Product Acquisiton Price.");
            return false;
        }
        
        repository.save(product);
        return true;
    }
}
