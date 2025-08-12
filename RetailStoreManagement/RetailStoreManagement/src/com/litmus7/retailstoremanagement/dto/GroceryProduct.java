package com.litmus7.retailstoremanagement.dto;

public class GroceryProduct extends Product{
	private String expiryDate;
    private double weightKg;

    public GroceryProduct(int productId, String productName, double price, String status,String expiryDate, double weightKg) {
        super(productId, productName, price, status);
        this.expiryDate = expiryDate;
        this.weightKg = weightKg;
    }
}
