package com.shop.e_cart.service;

import javax.servlet.http.HttpServletRequest;

import com.shop.e_cart.entity.Users;

/**
 * @author VAIBHAV
 */
public interface UserService {

	/**
	 * @param request
	 * @param model
	 * @auther - Vaibhav Garg
	 * @returntype - UserUtil
	 */
	String getUserDetails(HttpServletRequest request);

	Users addUser(HttpServletRequest request);

}
