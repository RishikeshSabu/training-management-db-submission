package com.litmus7.retailstoremanagement.util;

import com.litmus7.retailstoremanagement.dto.Product;
import java.util.Comparator;

public class NameComparator implements Comparator<Product> {
	public int compare(Product product1, Product product2) {
		return product1.getProductName().compareToIgnoreCase(product2.getProductName());
	}
}
