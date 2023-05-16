package com.shop.e_cart.service;

import java.util.List;

import com.shop.e_cart.entity.Products;

/**
 * @author - Vaibhav Garg
 */

public interface ProductService {

	/**
	 * @auther - Vaibhav Garg
	 * @date - May 13, 2023
	 * @returntype - List<Product>
	 */
	public List<Products> getAllProducts();
}
