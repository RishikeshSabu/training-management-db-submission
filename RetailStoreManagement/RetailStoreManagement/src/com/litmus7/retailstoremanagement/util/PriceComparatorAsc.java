package com.litmus7.retailstoremanagement.util;

import com.litmus7.retailstoremanagement.dto.Product;

import java.util.Comparator;

public class PriceComparatorAsc implements Comparator<Product> {
	public int compare(Product t1,Product t2) {
		return Double.compare(t1.getPrice(),t2.getPrice());
	}
}
