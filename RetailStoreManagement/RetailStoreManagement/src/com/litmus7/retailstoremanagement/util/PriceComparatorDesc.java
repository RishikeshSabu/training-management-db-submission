package com.litmus7.retailstoremanagement.util;

import com.litmus7.retailstoremanagement.dto.Product;
import java.util.Comparator;

public class PriceComparatorDesc implements Comparator<Product> {
	public int compare(Product p1,Product p2) {
		return Double.compare(p2.getPrice(),p1.getPrice());
	}
}
