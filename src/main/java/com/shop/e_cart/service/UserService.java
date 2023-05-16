package com.shop.e_cart.service;

import javax.servlet.http.HttpServletRequest;

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

}
