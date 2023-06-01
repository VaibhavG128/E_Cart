package com.shop.e_cart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.e_cart.dao.ProductDao;
import com.shop.e_cart.entity.Cart;
import com.shop.e_cart.entity.Products;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Override
	public List<Products> getAllProducts() {
		return productDao.findAll();
	}

	@Override
	public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
		List<Cart> book = new ArrayList<>();
		if (cartList.size() > 0) {
			for (Cart item : cartList) {
				Optional<Products> row = getAllProductsById(item.getId());
				if (row.isPresent()) {
					Cart row1 = new Cart();
					row1.setId(row.get().getId());
					row1.setImage(row.get().getImage());
					row1.setCategory(row.get().getCategory());
					row1.setName(row.get().getName());
					row1.setPrice(row.get().getPrice());
					row1.setQuantity(item.getQuantity());
					book.add(row1);
				}
			}
		}
		return book;
	}

	@Override
	public Optional<Products> getAllProductsById(int id) {
		return productDao.findById(id);
	}

	@Override
	public double getTotalCartPrice(ArrayList<Cart> cartList) {
		double sum = 0;
		if (cartList.size() > 0) {
			for (Cart item : cartList) {
				Optional<Products> row = getAllProductsById(item.getId());
				if (row.isPresent()) {
					sum += row.get().getPrice() * item.getQuantity();
				}
			}
		}
		return sum;
	}

	@Override
	public void addProduct(Products products) {
		productDao.save(products);
	}

}
