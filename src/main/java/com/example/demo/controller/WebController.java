package com.example.demo.controller;



import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.*;
import com.example.demo.service.*;

@Controller
public class WebController {

	//new service

	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productService;

	@Autowired
	CartService cartService;
	
	@Autowired
	OrderService orderService;
	
	
	@GetMapping("/")
	public String index(Model model) {

		model.addAttribute("product",new Product());
		
		model.addAttribute("ListProduct",productService.getAll());
		return "index";
	}
	

	
	@GetMapping("/login")
	public String login(Model model) {		
		model.addAttribute("user", new User());
		model.addAttribute("product",new Product());
		return "login";
	}
	
	@PostMapping("/login")
	public String login_Control(Model model,@ModelAttribute User user, HttpSession session) {
		model.addAttribute("product",new Product());
		User userRes = userService.checkLogin(user.getUsername(), user.getPassword());
		if (userRes == null) {
			model.addAttribute("error","wrong username or password.");
			return login(model);
		}
		else {
			session.setAttribute("user",userRes);
			return index(model);
		}
	}
	
	@GetMapping("/signin")
	public String signin(Model model) {
		model.addAttribute("product",new Product());
		model.addAttribute("User",new User());
		return "signin";
	}
	
	@PostMapping("/signin")
	public String signinSubmit(Model model,@ModelAttribute User user) {
		model.addAttribute("product",new Product());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		if (userService.checkUsername(user.getUsername())) {
			userService.insertUser(user);
			return "login";
		}
		model.addAttribute("error","username đã tồn tại");
		model.addAttribute("User",user);
		
		return "signin";
	}
	
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session, Model model) {
		model.addAttribute("product",new Product());
		session.removeAttribute("user");
		return index(model);
	}
	
	@GetMapping(value = "/viewpro")
	public String viewPro(HttpSession session,Model model, @RequestParam(value= "ID", required = true) Integer id) {
		model.addAttribute("product",new Product());
		Optional<Product> product = productService.ViewProById(id);
		
		Product res = product.get();
		model.addAttribute("productincart", new Item());
		session.setAttribute("this_product",res);
		model.addAttribute("ListProduct",productService.getAll());
		return "viewpro" ;
		
	}
	
	@GetMapping("/showpro")
	public String show(Model model, @RequestParam(value= "type", required = true) String type) {
		model.addAttribute("ListProduct",productService.getAll());
		model.addAttribute("product",new Product());
		model.addAttribute("ListResult",productService.viewProsByType(type));
		return "showpro";
	}

	@PostMapping("/showpro")
	public String show(Model model,@ModelAttribute Product product) {
		
		model.addAttribute("ListProduct",productService.getAll());
		
		model.addAttribute("ListResult",productService.findProByName(product.getName()));
		
		model.addAttribute("product",new Product());
		return "showpro";
	}
	
	@GetMapping("/viewcart")
	public String viewcart(Model model, HttpSession session) {
		model.addAttribute("product",new Product());
		model.addAttribute("order",new Order());
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return login(model);
		}
		
		Cart cart = cartService.getCart(user.getId());
		
		model.addAttribute("Cart",cart);
		model.addAttribute("Items", cart.getItems());
		
		return "viewcart";
	}
	
	@PostMapping("/addcart")
	public String addCart(Model model,HttpSession session, @ModelAttribute Item item) {
		User user = (User) session.getAttribute("user");
		
		model.addAttribute("product",new Product());
		if (user == null) {
			return login(model);
		}
		Cart cart = cartService.getCart(user.getId());
		
		item.setProduct((Product) session.getAttribute("this_product"));
		item.setCart(cart);
		cartService.insertItem(item);
		
		return viewPro(session, model, item.getProduct().getId());
	}
	
	@GetMapping("/deleteitem")
	public String deleteItem(HttpSession session,Model model,@RequestParam(value = "ID", required = true) Integer id) {
		cartService.deleteItem(id);
		return viewcart(model, session);
	}
	
	@GetMapping("/edititem")
	public String editItem(HttpSession session,Model model,@RequestParam(value = "ID", required = true) Integer id) {
		User user = (User) session.getAttribute("user");
		
		model.addAttribute("product",new Product());
		if (user == null) {
			return login(model);
		}
		model.addAttribute("product",new Product());
		model.addAttribute("item",cartService.getItem(id));
		model.addAttribute("ListProduct",productService.getAll());
		return "edititem";
	}
	
	@PostMapping("/edititem")
	public String editItem(HttpSession session, Model model,@ModelAttribute Item item) {
		model.addAttribute("product",new Product());
		cartService.updateItem(item);
		return viewcart(model, session);
	}
	
	@GetMapping("/vieworder")
	public String viewOrder(HttpSession session,Model model) {
		User user = (User) session.getAttribute("user");
		
		model.addAttribute("product",new Product());
		if (user == null) {
			return login(model);
		}
		
		model.addAttribute("product",new Product());
		model.addAttribute("ListOrder",orderService.findAllOrder(user.getId()));
		return "vieworder";
	}
	
	@PostMapping("/makeorder")
	public String makeOrder(Model model, @ModelAttribute Order order, HttpSession session) {
		User user = (User) session.getAttribute("user");
		order.setCart(cartService.getCart(user.getId()));
		order.setIdUser(user.getId());

		orderService.insertOrder(order);

		cartService.insertNewCart(user.getId());
		return viewOrder(session,model);
	}
	
	
	@PostMapping("/signin/facebook")
	public String loginFacebook(Model model, HttpSession session) {
		System.out.println("fb");
		String username = userService.getUserFB();
		
		if (userService.checkUsername(username)) {
			User user = new User();
			user.setUsername(username);
			user.setPassword("123456");
			userService.insertUser(user);
		}
		User res = userService.checkLogin(username, "123456");
		session.setAttribute("user", res);
		return index(model);
	}
	
}
