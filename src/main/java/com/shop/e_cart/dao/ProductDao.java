package com.shop.e_cart.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.shop.e_cart.entity.Products;

@Repository
public interface ProductDao extends JpaRepository<Products, Integer> {

}
