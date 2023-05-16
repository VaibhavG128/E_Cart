package com.shop.e_cart.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shop.e_cart.entity.Products;
import com.shop.e_cart.service.ProductService;
import com.shop.e_cart.service.UserService;

@Controller
@RequestMapping("/")
/**
 * @author VAIBHAV
 */
public class ECartController {

	@Autowired
	ProductService productService;

	@Autowired
	UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHomePage(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Products> list = productService.getAllProducts();
			request.setAttribute("productList", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage() {
		return "login";
	}

	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public String validate(HttpServletRequest request, HttpServletResponse response) {
		String page = userService.getUserDetails(request);
		if (page.equalsIgnoreCase("index"))
			return "redirect:/";
		else
			return "redirect:/login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		if(request.getSession().getAttribute("auth")!=null) {
			request.getSession().removeAttribute("auth");
			return "redirect:/";
		}else {
			return "redirect:/login";
		}
	}

}
