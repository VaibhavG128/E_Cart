package com.shop.e_cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.e_cart.dao.ProductDao;
import com.shop.e_cart.entity.Products;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;
	@Override
	public List<Products> getAllProducts() {
		return productDao.findAll();
	}

}
