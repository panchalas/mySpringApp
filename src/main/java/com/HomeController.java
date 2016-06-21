package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.Gson;

import com.service.ProductService;
import com.model.*;

@Controller
public class HomeController {

	@Autowired
	ProductService pservice;
	static String deleteRedirectPath="";
	public HomeController() {
	}

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
	public ModelAndView delProduct(@RequestParam("id") int id) {
		pservice.deleteRow(id);
		return new ModelAndView("index");
		//System.out.println(deleteRedirectPath);
		//return "redirect:"+deleteRedirectPath;
	}
	
	// --------------------------------------------------------------------------start_edit-save
	@RequestMapping(value = "/edit", method = RequestMethod.GET)	//edit link clicked
	public ModelAndView editProduct(@RequestParam("id") int id) {
		Product prod = pservice.getProductById(id);
		return new ModelAndView("editProduct", "editrow", prod);
	}

	@ModelAttribute("cmd_save_editedProduct")
	public Product constructEditedProduct() {
		return new Product();
	}

	@RequestMapping(value = "/saveEditedProduct", method = RequestMethod.POST)
	public String editProduct(@Validated @ModelAttribute("cmd_save_editedProduct") Product product, BindingResult result) 
	{
		if (result.hasErrors()) 
		{
			for (ObjectError lst : result.getAllErrors()) 
			{
				System.out.println(lst.toString());
			}
			return "redirect:editProduct";
		} 
		else 
		{
				pservice.updateRow(product);
				//List<Product> lsts = pservice.getList();
				//return new ModelAndView("ProductLists", "prdList", lsts);
				deleteRedirectPath="listProducts?cat=" + product.getCategory().toString();
				return "redirect:listProducts?cat=" + product.getCategory().toString();
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
	public String saveProduct(@ModelAttribute("Product") Product product, BindingResult result)
	// public ModelAndView saveProduct(@ModelAttribute("Product") Product
	// product, BindingResult result)
	{
		System.out.println(product.getId());
		System.out.println(product.getPrdName());
		System.out.println(product.getCategory());
		// int maxid=pservice.dbGetMaxID()+1;
		// product.setId(maxid);
		if (product.getCategory().equals("camera")) {
			product.setImgPath("/resources/images/cameras/");
		} else if (product.getCategory().equals("ehdd")) {
			product.setImgPath("/resources/images/ehdds/");
		} else if (product.getCategory().equals("tablet")) {
			product.setImgPath("/resources/images/tablets/");
		} else {
			product.setImgPath("/resources/images/others/");
		}

		if (result.hasErrors()) {
			return "redirect:addProduct";
		} else {
			// System.out.println(product.getPrdCat());
			pservice.insertRow(product);

			// List<Product> lsts =
			// pservice.getByCategory(product.getCategory());
			// return new ModelAndView(product.getCategory(),"prdList", lsts);
			// return new
			// ModelAndView("listProducts?cat="+product.getCategory());

			// return new ModelAndView("addProduct");

			return "redirect:listProducts?cat=" + product.getCategory().toString();
		}
	}
	// -------------------------------------------------------------------------------------end_save

	// ------------------------------------------------------------------------------------start-listing
	@RequestMapping(value = "/tablet", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String show(Model model) {
		model.addAttribute("Product", new Product());
		model.addAttribute("tablist", pservice.getByCategory("tablet"));
		String json = new Gson().toJson(pservice.getByCategory("tablet"));
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

	@RequestMapping(value = "/edit/{cat}", method = RequestMethod.POST)
	public String editProduct(@PathVariable("cat") String cat, Model model) {
		model.addAttribute("pr", this.pservice.getByCategory(cat));
		model.addAttribute("prlist", this.pservice.getList());
		return "pr";
	}
}
