package com;

import java.security.Principal;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.Gson;

import com.service.ProductService;
//import com.service.ProductService;
import com.model.Product;

@Controller
public class HomeController {

	@Autowired
	ProductService pservice;
	static String deleteRedirectPath = "";

	@Autowired
	ServletContext srv;

	public HomeController() {
	}

	// --------------------------------------------------------------- start-login 
		@RequestMapping(value="/signin", method=RequestMethod.GET)
		public String loginPage() {
		//ModelAndView mv = new ModelAndView("login");
			return "login";
		}
		
		//Spring Security see this :
		@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
		public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

			ModelAndView model = new ModelAndView();
			if (error != null) {
				model.addObject("error", "Invalid username and password!");
				System.out.println("error");
			}

			if (logout != null) {
				model.addObject("msg", "You've been logged out successfully.");
				System.out.println("logout");
			}
			model.setViewName("/admin/addProduct");
			System.out.println("login");
			return model;
		}

		@RequestMapping(value="/enter", method=RequestMethod.GET)
		public String enterPage(ModelMap model, Principal principal) {
			String name=principal.getName();
			model.addAttribute("username", name);
			model.addAttribute("message","Spring Security Basic Form Example");
			return "enter";
		}

		@RequestMapping(value="/loginfailed",  method=RequestMethod.GET)
		public String loginerror(ModelMap model) {
			model.addAttribute("error", "true");
			return "login";
		}
		
		@RequestMapping(value = "/logout", method = RequestMethod.GET)
		public String logoutPage(HttpServletRequest request, HttpServletResponse response) 
		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null)
			{
				new SecurityContextLogoutHandler().logout(request, response, auth);
			}
			return "/index";
			// You can redirect wherever you want, but generally
				// it's a good practice to show login screen again​
		}
		
		// --------------------------------------------------------------- end-login
		
	@RequestMapping(value = "/listAllProducts", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String listProducts(Model model) {
		model.addAttribute("Product", new Product());
		model.addAttribute("plist", this.pservice.getList());
		String json = new Gson().toJson(pservice.getList());
		return json;
	}
	/*
	 * @RequestMapping(value = "/addProduct", method = RequestMethod.GET) public
	 * ModelAndView addProduct(@ModelAttribute("prod") Product prod) { if
	 * (prod.getId() == 0) { this.pservice.insertRow(prod); } else {
	 * this.pservice.updateRow(prod); } return new ModelAndView("index"); }
	 */

	@ModelAttribute("pr")
	public Product addProduct() {
		return new Product();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	//public ModelAndView delProduct(@RequestParam("id") int id) {
	public String delProduct(@RequestParam("id") int id) {
		pservice.deleteRow(id);
		//return new ModelAndView("index");
		 System.out.println("redirect:"+deleteRedirectPath);
		
		 return "redirect:"+deleteRedirectPath;
	}

	// --------------------------------------------------------------------------start_edit-save
	@RequestMapping(value = "/edit", method = RequestMethod.GET) // edit link
																	// clicked
	public ModelAndView editProduct(@RequestParam("id") int id) {
		Product prod = pservice.getProductById(id);
		return new ModelAndView("editProduct", "editrow", prod);
	}

	@ModelAttribute("cmd_save_editedProduct")
	public Product constructEditedProduct() {
		return new Product();
	}

	@RequestMapping(value = "/saveEditedProduct", method = RequestMethod.POST)
	public String editProduct(@Valid @ModelAttribute("cmd_save_editedProduct") Product product,
			BindingResult result) {
		if (result.hasErrors()) {
			for (ObjectError lst : result.getAllErrors()) {
				System.out.println(lst.toString());
			}
			return "redirect:editProduct";
		} else {
			String path = srv.getRealPath("/");
			String res = product.getFilePath(path, srv.getContextPath(),product.getCategory());
			System.out.println(res);
			if (res == "fail")
				return "redirect:editProduct";
			else {

				pservice.updateRow(product);
				// List<Product> lsts = pservice.getList();
				// return new ModelAndView("ProductLists", "prdList", lsts);
				deleteRedirectPath = "listProducts?cat=" + product.getCategory().toString();
				return "redirect:listProducts?cat=" + product.getCategory().toString();
			}
		}
	}

	// -----------------------------------------------------------------------------------end_edit-save

	// ------------------------------------------------------------------------------start_save

	@ModelAttribute("Product")
	public Product constructProduct() {
		return new Product();
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	// @RequestMapping(value = "/ProductAdd", method = RequestMethod.GET)
	public ModelAndView Product_Add() {
		Product prd = new Product();
		// ModelAndView obj = new ModelAndView("ProductAdd");
		ModelAndView obj = new ModelAndView("addProduct");
		obj.addObject(prd);
		return obj;
	}

	@RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
	public String saveProduct(@Valid @ModelAttribute("Product") Product product, BindingResult result)
	// public ModelAndView saveProduct(@ModelAttribute("Product") Product
	// product, BindingResult result)
	{
		System.out.println(product.getId());
		System.out.println(product.getPrdName());
		System.out.println(product.getCategory());
		/*
		if (product.getCategory().equals("camera")) {
			product.setImgPath("/resources/images/cameras/");
		} else if (product.getCategory().equals("ehdd")) {
			product.setImgPath("/resources/images/ehdds/");
		} else if (product.getCategory().equals("tablet")) {
			product.setImgPath("/resources/images/tablets/");
		} else {
			product.setImgPath("/resources/images/others/");
		}*/

		if (result.hasErrors()) {
			return "redirect:addProduct";
		} else {
			String path = srv.getRealPath("/");
			System.out.println(path);
			System.out.println(srv.getContextPath());
			String res = product.getFilePath(path, srv.getContextPath(),product.getCategory());
			//System.out.println(res);
			if (res == "fail")
				return "redirect:addProduct";
			else {
				pservice.insertRow(product);
				// return new ModelAndView("addProduct");
				return "redirect:listProducts?cat=" + product.getCategory().toString();
			}
		}
	}
	// -------------------------------------------------------------------------------------end_save

	// ------------------------------------------------------------------------------------start-listing
	@RequestMapping(value = "/tablet", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String show(Model model) {
		model.addAttribute("Product", new Product());
		model.addAttribute("tablist", pservice.getByCategory("tablet"));
		String json = new Gson().toJson(pservice.getByCategory("tablet"));
		deleteRedirectPath = "listProducts?cat=tablet";
		if (json == "")
			return "No Record Found";
		else
			return json;
	}

	@RequestMapping(value = "/camera", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String showcam(Model model) {
		model.addAttribute("Product", new Product());
		model.addAttribute("tablist", pservice.getByCategory("camera"));
		String json = new Gson().toJson(pservice.getByCategory("camera"));
		deleteRedirectPath = "listProducts?cat=camera";
		if (json == "")
			return "No Record Found";
		else
			return json;
	}

	@RequestMapping(value = "/ehdd", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String showehdd(Model model) {
		model.addAttribute("Product", new Product());
		model.addAttribute("ehdlist", pservice.getByCategory("ehdd"));
		String json = new Gson().toJson(pservice.getByCategory("ehdd"));
		deleteRedirectPath = "listProducts?cat=ehdd";
		if (json == "")
			return "No Record Found";
		else
			return json;
	}

	@RequestMapping("/listProducts")
	public ModelAndView showProducts() {
		ModelAndView model = new ModelAndView("listProducts");
		return model;
	}
	// ------------------------------------------------------------------------------------end-listing
	
	@RequestMapping("/")
	public ModelAndView indexPage() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}

	@RequestMapping("/index")
	public ModelAndView indexPage2() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	
	@RequestMapping("/contactUs")
	public ModelAndView contactPage() {
		ModelAndView mv = new ModelAndView("contactUs");
		return mv;
	}
	
	@RequestMapping("/aboutUs")
	public ModelAndView aboutPage() {
		ModelAndView mv = new ModelAndView("aboutUs");
		return mv;
	}
	
	@RequestMapping(value = "/edit/{cat}", method = RequestMethod.POST)
	public String editProduct(@PathVariable("cat") String cat, Model model) {
		model.addAttribute("pr", this.pservice.getByCategory(cat));
		model.addAttribute("prlist", this.pservice.getList());
		return "pr";
	}
}
