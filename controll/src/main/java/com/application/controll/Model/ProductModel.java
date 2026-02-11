/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.controll.Model;

import java.util.UUID;

/**
 *
 * @author ruan
 */
public class ProductModel {
    private UUID    productId                  =    null    ;
    private String  productCode                =    ""      ;
    private String  productName                =    ""      ;
    private String  productDescription         =    ""      ;
    private Double  productBasePrice           =    0.0     ;
    private Double  productPrice               =    0.0     ;
    private Double  productAcquisitionPrice    =    0.0     ;
    private Double  productTAX                 =    0.0     ;
    private Integer productAmount              =    0       ;
    private String  productCategory            =    ""      ;
    private Boolean productIsActive            =    true    ;
    
    
    /// Empty Constructor
    public ProductModel() {
    }
    
    /// Constructor for Insert a new Data
    public ProductModel(String productCode, String productName, String productDescription, Double productBasePrice, Double productPrice, Double productAcquisitionPrice, Double productTAX, Integer productAmount, String productCategory, Boolean productIsActive) {
        this.productId                 =    UUID.randomUUID()        ;
        this.productCode               =    productCode              ;
        this.productName               =    productName              ;
        this.productDescription        =    productDescription       ;
        this.productBasePrice          =    productBasePrice         ;
        this.productPrice              =    productPrice             ;
        this.productAcquisitionPrice   =    productAcquisitionPrice  ;
        this.productTAX                =    productTAX               ;
        this.productAmount             =    productAmount            ;
        this.productCategory           =    productCategory          ;
        this.productIsActive           =    productIsActive          ;
    }
    
    /// Constructor for Update, Read and Delete Query
    public ProductModel(UUID productId, String productCode, String productName, String productDescription, Double productBasePrice, Double productPrice, Double productAcquisitionPrice, Double productTAX, Integer productAmount, String productCategory, Boolean productIsActive) {
        this.productId                 =    productId                ;
        this.productCode               =    productCode              ;
        this.productName               =    productName              ;
        this.productDescription        =    productDescription       ;
        this.productBasePrice          =    productBasePrice         ;
        this.productPrice              =    productPrice             ;
        this.productAcquisitionPrice   =    productAcquisitionPrice  ;
        this.productTAX                =    productTAX               ;
        this.productAmount             =    productAmount            ;
        this.productCategory           =    productCategory          ;
        this.productIsActive           =    productIsActive          ;
    }
    
    public UUID getProductId() {
        return productId;
    }
    
    public void setProductId(UUID productId) {
        this.productId = productId;
    }
    
    public String getProductCode() {
        return productCode;
    }
    
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public String getProductDescription() {
        return productDescription;
    }
    
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    
    public Double getProductBasePrice() {
        return productBasePrice;
    }
    
    public void setProductBasePrice(Double productBasePrice) {
        this.productBasePrice = productBasePrice;
    }
    
    public Double getProductPrice() {
        return productPrice;
    }
    
    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }
    
    public Double getProductAcquisitionPrice() {
        return productAcquisitionPrice;
    }
    
    public void setProductAcquisitionPrice(Double productAcquisitionPrice) {
        this.productAcquisitionPrice = productAcquisitionPrice;
    }
    
    public Double getProductTAX() {
        return productTAX;
    }
    
    public void setProductTAX(Double productTAX) {
        this.productTAX = productTAX;
    }
    
    public Integer getProductAmount() {
        return productAmount;
    }
    
    public void setProductAmount(Integer productAmount) {
        this.productAmount = productAmount;
    }
    
    public String getProductCategory() {
        return productCategory;
    }
    
    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
    
    public Boolean getProductIsActive() {
        return productIsActive;
    }
    
    public void setProductIsActive(Boolean productIsActive) {
        this.productIsActive = productIsActive;
    }
}
