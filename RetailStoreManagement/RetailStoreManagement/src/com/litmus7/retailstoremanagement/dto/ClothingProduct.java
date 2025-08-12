package com.litmus7.retailstoremanagement.dto;

public class ClothingProduct extends Product {
	private String size;
	private String material;
	
	public ClothingProduct(int productId, String productName, double price, String status,String size, String material) {
		super(productId, productName, price, status);
		this.size = size;
		this.material = material;
	}
	
}
