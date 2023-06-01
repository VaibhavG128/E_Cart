package com.shop.e_cart.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.e_cart.entity.Orders;

public interface OrderDao extends JpaRepository<Orders, Integer> {

	List<Orders> findAllByuId(int id);
}
