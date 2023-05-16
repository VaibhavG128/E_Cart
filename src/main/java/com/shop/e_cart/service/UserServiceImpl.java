package com.shop.e_cart.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.e_cart.dao.UserDao;
import com.shop.e_cart.entity.Users;
import com.shop.e_cart.util.UserUtil;

/**
 * @author VAIBHAV GARG
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	ProductService productService;

	@Override
	public String getUserDetails(HttpServletRequest request) {
		String email = request.getParameter("login-email");
		String password = request.getParameter("login-password");
		Users users = userDao.findOneByEmail(email);
		String page = "login";
		UserUtil userUtil = new UserUtil();
		if (users != null && users.getPassword().equalsIgnoreCase(password)) {
			userUtil.setId(users.getId());
			userUtil.setName(users.getName());
			userUtil.setEmail(users.getEmail());
			request.getSession().setAttribute("auth", userUtil);
			page = "index";
		}
		return page;
	}

}
