package com.shop.e_cart.service;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.shop.e_cart.entity.Cart;
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
	
	public Optional<Products> getAllProductsById(int id);

	public List<Cart> getCartProducts(ArrayList<Cart> cart_list);

	public double getTotalCartPrice(ArrayList<Cart> cart_list);

	public void addProduct(Products products);
}
