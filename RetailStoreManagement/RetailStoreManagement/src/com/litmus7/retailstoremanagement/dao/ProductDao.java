package com.litmus7.retailstoremanagement.dao;

import com.litmus7.retailstoremanagement.dto.Product;
import com.litmus7.retailstoremanagement.exception.ProductDaoException;

import java.util.ArrayList;
import java.util.List;

public class ProductDao {
	 private List<Product> products = new ArrayList<>();
	 public boolean addProduct(Product p) throws ProductDaoException {
	        products.add(p);
	        //store products in CSV file
	        //throw productDaoException if error occurs
	        return false;
	    }
	 
	 public List<Product> getAllProducts() throws ProductDaoException{
		 //read datas from CSV
		//throw productDaoException if error occurs
	     return null;
	    }
	 public List<Product> viewProductsByCategory(String category) throws ProductDaoException{
		 //read file from csv
		 //iterate through each product
		 //check whether the product is instance of given category
		 //return the filtered product
		 //throw ProductDaoException if error occurs
		 return null;
	 }
}
