package javaprojectgit.controller;


import java.sql.SQLException;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javaprojectgit.exception.UserValidationException;
import javaprojectgit.model.User;
import javaprojectgit.services.UserService;


@Controller
@RequestMapping(value="/login")
@SessionAttributes("currentUser")
public class LoginController {
	@Autowired
	UserService userService;
	
	@ModelAttribute("user")
	   public User setUpUserForm() {
	      return new User();
	   }
	
	@RequestMapping("/")
	public ModelAndView index() {

		return new ModelAndView("login");
	}
	@RequestMapping("/admin")
	public ModelAndView adminlogin() {
		return new ModelAndView("/AdminScreen/Login");
	}
	
	 @PostMapping("/dologin")
	public ModelAndView loginProcess( Model  model, @ModelAttribute("user") User customer) 
	 {
		
		customer.setRoleId(2);
		try {
			userService.validation(customer);
			customer= userService.authorize(customer);	
			model.addAttribute("currentUser",customer);
			return new ModelAndView("redirect:/user/profile");
			}catch(UserValidationException uva) {
				model.addAttribute("message",uva.getMessage());
				return new ModelAndView("login");
			
			}catch(SQLException sqle) {
					model.addAttribute("message",sqle.getMessage());
					return new ModelAndView("login");
			}catch(Exception e) {
				model.addAttribute("message",e.getMessage());
				return new ModelAndView("login");
			}
		
				
	}
	 
	 @PostMapping(value="/adminloginsubmit")
	public ModelAndView adminLoginSubmit(Model  model, @ModelAttribute("user") User admin) 
			{
		
		admin.setRoleId(1);
		try {
			userService.validation(admin);
			admin= userService.authorize(admin); 
			model.addAttribute("currentUser",admin);
			return new ModelAndView("redirect:/user/adminDashboard");
				
		}catch(UserValidationException uva) {
			model.addAttribute("message",uva.getMessage());
			return new ModelAndView("/AdminScreen/Login");
		
		}catch(SQLException sqle) {
				model.addAttribute("message",sqle.getMessage());
				return new ModelAndView("/AdminScreen/Login");
		}catch(Exception e) {
			model.addAttribute("message",e.getMessage());
			return new ModelAndView("/AdminScreen/Login");
		}
							
	}
	 @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	 public ModelAndView showUser(Model  model, @PathVariable("id") int id) {
		 try {
			User user = userService.findById(id);
			model.addAttribute("user",user);
		} catch (UserValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return new ModelAndView("/AdminScreen/Users/EditUser");
	 
	 }


}