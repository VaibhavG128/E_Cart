package com.shop.e_cart.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.shop.e_cart.entity.Cart;
import com.shop.e_cart.entity.Order;
import com.shop.e_cart.entity.Orders;
import com.shop.e_cart.entity.Products;
import com.shop.e_cart.entity.Users;
import com.shop.e_cart.service.OrderService;
import com.shop.e_cart.service.ProductService;
import com.shop.e_cart.service.UserService;
import com.shop.e_cart.util.ProductUtil;
import com.shop.e_cart.util.UserUtil;

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

	@Autowired
	OrderService orderService;

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
		if (request.getSession().getAttribute("auth") != null) {
			request.getSession().removeAttribute("auth");
			return "redirect:/";
		} else {
			return "redirect:/login";
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addToCart", method = RequestMethod.GET)
	public String addToCart(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		System.err.println("in");
		ArrayList<Cart> cartList = new ArrayList<>();
		JSONObject jsonObject = new JSONObject();
		int id = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		Cart cm = new Cart();
		cm.setId(id);
		cm.setQuantity(1);
		ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart_list");
		JSONObject jsonObjectPrev = (JSONObject) session.getAttribute("cartList");
		if (cart_list == null) {
			cartList.add(cm);
			jsonObject.put(request.getParameter("id"), id);
			session.setAttribute("cart_list", cartList);
			session.setAttribute("cartList", jsonObject);
		} else {
			System.out.println(request.getParameter("cartList"));

			jsonObject = jsonObjectPrev;
			cartList = cart_list;
			boolean exist = false;
			for (Cart c : cart_list) {
				if (c.getId() == id) {
					System.out.println("else else==>" + cart_list);
					exist = true;
					// out.println("<h3 style='color:crimson; text-align: center'>Item Already in
					// Cart. <a href='cart.jsp'>GO to Cart Page</a></h3>");
				}
			}

			if (!exist) {
				System.out.println("else else=====>" + cart_list);
				cartList.add(cm);
				jsonObject.put(request.getParameter("id"), id);
			}
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String cart(HttpServletRequest request, HttpServletResponse response) {
		DecimalFormat dcf = new DecimalFormat("#.##");
		request.setAttribute("dcf", dcf);
		UserUtil auth = (UserUtil) request.getSession().getAttribute("auth");
		if (auth != null) {
			request.setAttribute("person", auth);
		}
		ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart_list");
		List<Cart> cartProduct = null;
		if (cart_list != null) {
			Products pDao = new Products();
			cartProduct = productService.getCartProducts(cart_list);
			double total = productService.getTotalCartPrice(cart_list);
			request.setAttribute("total", total);
			request.setAttribute("cart_list", cartProduct);
		}
		return "cart";
	}

	@RequestMapping(value = "/orderNow", method = RequestMethod.GET)
	public String orderNow(HttpServletRequest request, HttpServletResponse response) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String returnType = "/";
		UserUtil auth = (UserUtil) request.getSession().getAttribute("auth");
		if (auth != null) {
			String productId = request.getParameter("id");
			int productQuantity = Integer.parseInt(request.getParameter("quantity"));
			if (productQuantity <= 0) {
				productQuantity = 1;
			}
			Orders orderModel = new Orders();
			orderModel.setpId(Integer.parseInt(productId));
			orderModel.setuId(auth.getId());
			orderModel.setQunatity(productQuantity);
			orderModel.setDate(formatter.format(date));
			Orders orders = orderService.saveOrder(orderModel);
			System.out.println(orders);
			if (orders != null) {
				@SuppressWarnings("unchecked")
				ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart_list");
				JSONObject cartList = (JSONObject) request.getSession().getAttribute("cartList");
				if (cart_list != null) {
					for (Cart c : cart_list) {
						if (c.getId() == Integer.parseInt(productId)) {
							cart_list.remove(cart_list.indexOf(c));
							cartList.remove(String.valueOf(c.getId()));
							System.out.println(cart_list + "==>" + cartList);
							break;
						}
					}
				}
				returnType = "order";
			} else {
			}
		} else {
			returnType = "login";
		}
		return "redirect:/" + returnType;

	}

	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String order(HttpServletRequest request, HttpServletResponse response) {
		List<Order> orderList;
		String responseType;
		UserUtil auth = (UserUtil) request.getSession().getAttribute("auth");
		if (auth != null) {
			request.setAttribute("person", auth);
			orderList = orderService.getOrdersList(auth.getId());
			request.setAttribute("ordersList", orderList);
			responseType = "orders";
		} else {
			responseType = "login";
		}
		ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart_list");
		JSONObject cartList = (JSONObject) request.getSession().getAttribute("cartList");
		if (cart_list != null) {
			request.setAttribute("cart_list", cart_list);
			request.getSession().setAttribute("cartList", cartList);
		}
		return responseType;
	}

	@RequestMapping(value = "/cancelOrder", method = RequestMethod.GET)
	public String cancelOrder(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		if (id != null) {
			orderService.cancelOrder(Integer.parseInt(id));
		}
		return "redirect:/order";
	}

	@RequestMapping(value = "/quantity", method = RequestMethod.GET)
	public String quantity(HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter("action");
		int id = Integer.parseInt(request.getParameter("id"));
		ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart_list");
		if (action != null && id >= 1) {
			if (action.equals("inc")) {
				for (Cart c : cart_list) {
					if (c.getId() == id) {
						int quantity = c.getQuantity();
						quantity++;
						c.setQuantity(quantity);
					}
				}
			}

			if (action.equals("dec")) {
				for (Cart c : cart_list) {
					if (c.getId() == id && c.getQuantity() > 1) {
						int quantity = c.getQuantity();
						quantity--;
						c.setQuantity(quantity);
						break;
					}
				}
			}
		} else {
		}
		return "redirect:/cart";
	}

	@RequestMapping(value = "/removeFromCart", method = RequestMethod.GET)
	public String removeFromCart(HttpServletRequest request, HttpServletResponse response) {
		String bookId = request.getParameter("id");
		if (bookId != null) {
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart_list");
			JSONObject cartList = (JSONObject) request.getSession().getAttribute("cartList");
			if (cart_list != null) {
				for (Cart c : cart_list) {
					if (c.getId() == Integer.parseInt(bookId)) {
						cart_list.remove(cart_list.indexOf(c));
						cartList.remove(String.valueOf(c.getId()));
						System.out.println(cart_list + "==>" + cartList + "==>" + c.getId());
						break;
					}
				}
			}
		}
		return "redirect:/cart";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/cartCheckOut", method = RequestMethod.GET)
	public String cartCheckOut(HttpServletRequest request, HttpServletResponse response) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String responseType;
		ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart_list");
		JSONObject cartList = (JSONObject) request.getSession().getAttribute("cartList");
		UserUtil auth = (UserUtil) request.getSession().getAttribute("auth");
		if (cart_list != null && auth != null) {
			for (Cart c : cart_list) {
				Orders order = new Orders();
				order.setuId(auth.getId());
				order.setpId(c.getId());
				order.setQunatity(c.getQuantity());
				order.setDate(formatter.format(date));
				orderService.saveOrder(order);
			}
			cart_list.clear();
			cartList.clear();
			responseType = "order";
		} else {
			if (auth == null) {
				responseType = "login";
			}
			else
			{
				responseType = "cart";
			}
		}
		return "redirect:/" + responseType;
	}

	@RequestMapping(value = "/registrationProduct", method = RequestMethod.GET)
	public String registrationProduct(HttpServletRequest request, HttpServletResponse response) {
		return "registrationProduct";
	}
	
	@RequestMapping(value = "/registrationPage", method = RequestMethod.GET)
	public String registrationPage(HttpServletRequest request, HttpServletResponse response) {
		return "UserRegistration";
	}
	
	@RequestMapping(value = "/registerAction", method = RequestMethod.POST)
	public String registerAction(HttpServletRequest request, HttpServletResponse response) {
		Users user = userService.addUser(request);
		System.out.println(user);
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/registrationProductAction", method = RequestMethod.POST ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String registerAction(@ModelAttribute ProductUtil productUtil,BindingResult bindingResult) {
		Boolean status;
		for(int i=0;i<productUtil.getCategory().size();i++)
		{
			InputStream inputStream = null;
	        OutputStream outputStream = null;
			MultipartFile file = productUtil.getImage().get(i);
	        String name = file.getOriginalFilename();
	        File newFile = new File("C:\\Users\\VAIBHAV\\Eclipse_workspace_2\\e_cart\\src\\main\\resources\\static\\product-image\\" + name);
	        try {
	            inputStream = file.getInputStream();
	            if (!newFile.exists()) {
	                newFile.createNewFile();
	            }
	            outputStream = new FileOutputStream(newFile);
	            int read = 0;
	            byte[] bytes = new byte[1024];

	            while ((read = inputStream.read(bytes)) != -1) {
	                outputStream.write(bytes, 0, read);
	            }
	            status = true;
	        } catch (IOException e) {
	           status = false;
	        }
	        if(status)
	        {
	        	Products products = new Products();
				products.setName(productUtil.getName().get(i));
				products.setCategory(productUtil.getCategory().get(i));
				products.setPrice(Double.parseDouble(productUtil.getPrice().get(i).toString()));
				products.setImage(name);
				productService.addProduct(products);
	        }
		}
		return "redirect:/registrationProduct";
	}
	
}
