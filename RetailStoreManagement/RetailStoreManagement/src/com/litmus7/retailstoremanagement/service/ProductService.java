package com.litmus7.retailstoremanagement.service;

import java.util.Comparator;
import java.util.List;

import com.litmus7.retailstoremanagement.dao.ProductDao;
import com.litmus7.retailstoremanagement.dto.Product;
import com.litmus7.retailstoremanagement.exception.ProductServiceException;

public class ProductService {
	private ProductDao dao = new ProductDao();
	
	public boolean addProduct(Product product) throws ProductServiceException{
		//validation
		//call dao.addProduct()
		//throw throws ProductServiceException if error occurs
		return false;
	}
	 public List<Product> getAllProducts() throws ProductServiceException{
		 //return dao.getAllProducts();
		 //throw throws ProductServiceException if error occurs
		 return null;
	}
	 
	 public List<Product>viewProductsByCategory(String category) throws ProductServiceException{
		 //call viewProductByCategory
		 //throw throws ProductServiceException if error occurs
		 //return products based on category
		 return null;
	 }
	 public List<Product> sortProducts(List<Product> products, Comparator<Product> comparator) throws ProductServiceException{
		 //Collections.sort(products, comparator);
		 //return products;
		 //throw throws ProductServiceException if error occurs
		 return null;
		}
	 
}
