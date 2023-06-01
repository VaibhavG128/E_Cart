package com.shop.e_cart.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.e_cart.dao.OrderDao;
import com.shop.e_cart.entity.Order;
import com.shop.e_cart.entity.Orders;
import com.shop.e_cart.entity.Products;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDao orderDao;
	
	@Autowired
	ProductService productService;
	
	@Override
	public Orders saveOrder(Orders orderModel) {
		return orderDao.saveAndFlush(orderModel);
	}

	@Override
	public List<Order> getOrdersList(int id) {
		List<Orders> orderList = orderDao.findAllByuId(id);
		System.out.println(orderList);
		List<Order> ordersList = new ArrayList<>();
		Optional<Products> products;
		for(Orders orders: orderList)
		{
			Order order = new Order();
			products = productService.getAllProductsById(orders.getpId());
			order.setOrderId(orders.getOrderId());
			order.setDate(orders.getDate());
			order.setName(products.get().getName());
			order.setCategory(products.get().getCategory());
			order.setQunatity(orders.getQunatity());
			order.setPrice(products.get().getPrice());
			ordersList.add(order);
		}
		return ordersList;
	}

	@Override
	public void cancelOrder(int orderId) {
		orderDao.deleteById(orderId);
	}

}
