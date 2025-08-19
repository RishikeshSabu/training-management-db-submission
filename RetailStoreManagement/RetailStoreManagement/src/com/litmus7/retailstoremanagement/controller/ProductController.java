package com.litmus7.retailstoremanagement.controller;

import java.util.Comparator;
import java.util.List;

import com.litmus7.retailstoremanagement.dto.Product;
import com.litmus7.retailstoremanagement.dto.Response;
import com.litmus7.retailstoremanagement.service.ProductService;

public class ProductController {
	ProductService service=new ProductService();
	
	public Response<Boolean> addProduct(Product product){
		//check whether the input data is valid
		//call addProduct function from service
		//return response
		return null;
		
	}
	public Response<List<Product>>getAllProduct(){
		//call getAllProduct from service
		//return Response
		return null;
		
	}
	public Response<List<Product>>viewProductsbyCategory(String category){
		//call viewProductsByCategory from service
		//return response
		return null;
		
	}
	public Response<List<Product>> sortProducts(List<Product> products,String condition){
		//create objects of PriceComparatorAsc,PriceComparatorDesc,NameComparator based on condition
		//pass the created object as argument of sortProduct from service
		//sortProduct function from service
		//return response
		return null;
	}
}
