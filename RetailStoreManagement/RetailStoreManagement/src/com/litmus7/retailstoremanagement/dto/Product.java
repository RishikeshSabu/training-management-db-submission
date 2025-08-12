package com.litmus7.retailstoremanagement.dto;

public abstract class Product {
	protected int productId;
    protected String productName;
    protected double price;
    protected String status;

    public Product(int productId, String productName, double price, String status) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.status = status;
    }
    public double getPrice() {
    	return price;
    }
    public String getProductName() {
    	return productName;
    }
}
