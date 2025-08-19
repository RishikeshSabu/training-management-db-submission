package com.litmus7.retailstoremanagement.ui;

import java.util.List;

import com.litmus7.retailstoremanagement.controller.ProductController;
import com.litmus7.retailstoremanagement.dto.Product;
import com.litmus7.retailstoremanagement.dto.Response;
import com.litmus7.retailstoremanagement.util.NameComparator;
import com.litmus7.retailstoremanagement.util.PriceComparatorAsc;
import com.litmus7.retailstoremanagement.util.PriceComparatorDesc;

public class RetailStoreManagementApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProductController controller = new ProductController();
		//Add product
		//ask the category to user
		//create the object ElectronicProduct,ClothingProduct,GroceryProduct based on the category
		//now create the product and add
		Response<Boolean> addProductResponse=controller.addProduct(null);
		//print the data if status code is 200 else print error message
		
		
		//GetallProduct
		Response<List<Product>> getAllProductResponse=controller.getAllProduct();
		//display the response
		//viewProductByCategory
		//String category="Clothing";
		//pass the category as argument;
		Response<List<Product>> viewProductByCategoryResponse=controller.viewProductsbyCategory(null);
		//print response
		
		//sortProduct
		String priceSortAsc="Sort the product by their price in ascending order";
		String priceSortDesc="Sort the product by their price in descending order";
		String NameSortAsc="Sort the product by their name";
		//ask user to pick the comparator needed
		Response<List<Product>> sortProductResponse=controller.sortProducts(null,null);
		//print response
	}

}
