package com.shop.e_cart.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shop.e_cart.entity.Users;

/**
 * @author VAIBHAV GARG
 */
@Repository
public interface UserDao extends JpaRepository<Users, Integer> {
	
	public Users findOneByEmail(String email);

}
