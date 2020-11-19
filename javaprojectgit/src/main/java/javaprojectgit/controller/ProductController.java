package javaprojectgit.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javaprojectgit.model.ProductCategory;
import javaprojectgit.model.Products;
import javaprojectgit.model.User;
import javaprojectgit.services.ProductService;
import javaprojectgit.services.UserService;

@Controller
@RequestMapping(value="/product")
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;
	public static final String REST_SERVICE_URI = "http://localhost:8080/javaprojectgit/api/";
	/**
	 * To list the products in Admin side
	 * @param model
	 * @param currentUser
	 * @return
	 */
//	@GetMapping("/list")
//	@SuppressWarnings("unchecked")
//	public ModelAndView listProducts(Model model) {
//        RestTemplate restTemplate = new RestTemplate();
//        List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"/productlist", List.class);
//         
//        return new ModelAndView("/adminscreen/products/list");
//	}
	@GetMapping("/list")
	@SuppressWarnings("unchecked")
	public ModelAndView listProducts(Model model,  @SessionAttribute("currentUser") User currentUser ) {
		if(userService.isAdmin(currentUser)) {
			RestTemplate restTemplate = new RestTemplate();
		List<Products> listProducts=restTemplate.getForObject(REST_SERVICE_URI+"/productlist", List.class);
		 model.addAttribute("listProducts",listProducts);
		 return new ModelAndView("/adminscreen/products/list");
		}
		return new ModelAndView("redirect:/admin");
	}
	@GetMapping("/")
	public ModelAndView shop(Model model ) {
		List<Products> listProducts=productService.findAllproducts();
		 model.addAttribute("listProducts",listProducts);
		 return new ModelAndView("shop");
	}
	@GetMapping("/{id}")
	public ModelAndView productdetails(Model model,@PathVariable("id") int id,@SessionAttribute("currentUser") User currentUser , @ModelAttribute("products") Products products) {
		if(userService.isAdmin(currentUser)) {
		Products product =productService.findProductById(id);
		model.addAttribute("products",product);
		List<ProductCategory> listCategory=productService.findAllCategory();
		model.addAttribute("listCategory",listCategory);
		return new ModelAndView("/adminscreen/products/editproduct");	
		}
		return new ModelAndView("redirect:/admin");
	}
	@RequestMapping("/add")
	public ModelAndView addProducts(Model model,@SessionAttribute("currentUser") User currentUser ,@ModelAttribute("products") Products products) {
		if(userService.isAdmin(currentUser)) {
		List<ProductCategory> listCategory=productService.findAllCategory();
		model.addAttribute("listCategory",listCategory);
		return new ModelAndView("/adminscreen/products/addproduct");
		}
		return new ModelAndView("redirect:/admin");
	}
	@RequestMapping("/save")
	public ModelAndView save(Model model,@ModelAttribute("products") Products products) {
		productService.saveProduct(products);
		return new ModelAndView("redirect:/product/list");
	}

}
