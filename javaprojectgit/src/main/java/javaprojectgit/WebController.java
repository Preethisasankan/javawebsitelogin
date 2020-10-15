package javaprojectgit;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import javaprojectgit.model.User;
import javaprojectgit.services.UserService;
import javaprojectgit.services.UserValidationException;

@Controller
public class WebController {
	@Autowired
	UserService userService;
	
	@RequestMapping("/adminDashboard")
	public ModelAndView adminDashboard(Model model) {
		List<User> listCustomer=userService.getUser();
		model.addAttribute("listCustomer",listCustomer);
		return new ModelAndView("home");
	}
	@RequestMapping("/login")
	public ModelAndView login(Model model) {
		return new ModelAndView("login");
	}
	@RequestMapping("/adminlogin")
	public ModelAndView adminlogin(Model model) {
		return new ModelAndView("adminlogin");
	}
	@RequestMapping("/profile")
	public ModelAndView profile() {
		return new ModelAndView("profile");
	}
	@RequestMapping(value="/loginSubmit",method = RequestMethod.POST)
	public ModelAndView loginSubmit(@RequestParam("userName") String userName,@RequestParam("userPassword") String userPassword, ModelMap  model) 
	 {
		User customer = new User();
		customer.setUsername(userName);
		customer.setPassword(userPassword);
		customer.setRoleId(2);
		try {
			userService.validation(customer);
			customer= userService.authorize(customer);		 
			model.addAttribute("Name",customer.getName());
			return new ModelAndView("/profile", model);
			}catch(UserValidationException uva) {
				model.addAttribute("error",uva.getMessage());
				return new ModelAndView("login");
			}
		
				
	}
	@RequestMapping(value="/adminloginsubmit",method = RequestMethod.POST)
	public ModelAndView loginSubmit(@RequestParam("userName") String userName,@RequestParam("userPassword") String userPassword, Model  model) 
			{
		User admin = new User();
		admin.setUsername(userName);
		admin.setPassword(userPassword);
		admin.setRoleId(1);
		try {
			userService.validation(admin);
			admin= userService.authorize(admin); 
				model.addAttribute("Name",admin.getName());
				List<User> listCustomer=userService.getUser();
				model.addAttribute("listCustomer",listCustomer);
				return new ModelAndView("home");
				
		}catch(UserValidationException uva) {
			model.addAttribute("error",uva.getMessage());
			return new ModelAndView("login");
		}
							
	}
	

}