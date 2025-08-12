package com.litmus7.retailstoremanagement.dto;

public class ElectronicProduct extends Product {
	private String brand;
    private int warrantyMonths;
    
    public ElectronicProduct(int productId, String productName, double price, String status,String brand, int warrantyMonths) {
    	super(productId, productName, price, status);
    	this.brand = brand;
    	this.warrantyMonths = warrantyMonths;
    }
    
}