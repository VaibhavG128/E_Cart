package com.shop.e_cart.service;

import java.util.List;

import com.shop.e_cart.entity.Order;
import com.shop.e_cart.entity.Orders;

public interface OrderService {

	Orders saveOrder(Orders orderModel);

	List<Order> getOrdersList(int i);

	void cancelOrder(int orderId);

}
