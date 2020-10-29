package javaprojectgit.controller;


import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javaprojectgit.exception.UserValidationException;
import javaprojectgit.model.User;
import javaprojectgit.services.UserService;


@Controller
@RequestMapping(value="/")
@SessionAttributes("currentUser")
public class LoginController {
	@Autowired
	UserService userService;
	
	@ModelAttribute("currentUser")
	   public User setUpUserForm() {
	      return new User();
	   }
	@GetMapping("/home")
	public ModelAndView index() {
		return new ModelAndView("index");
	}
	
	@GetMapping("login")
	public ModelAndView login( @ModelAttribute("currentUser") User currentUser) {
		if(ObjectUtils.isEmpty(currentUser.id)) {
			return new ModelAndView("login");
		}else {
			return new ModelAndView("redirect:/user/profile");
		}
	}
	@GetMapping("admin")
	public ModelAndView adminlogin(Model  model,@ModelAttribute("currentUser") User currentUser) {
		
			if(userService.isAdmin(currentUser)) {
			return new ModelAndView("redirect:/user/dashboard");
			} 
			
		return new ModelAndView("/adminscreen/adminlogin");
	}
	
	 @PostMapping("dologin")
	public ModelAndView loginProcess( Model  model, @ModelAttribute("currentUser") User customer) 
	 {
		
		
		try {
			userService.validation(customer);
			customer.setRoleId(2);
			customer= userService.authorize(customer);	
			model.addAttribute("currentUser",customer);
			return new ModelAndView("redirect:/user/profile");
			}catch(UserValidationException uva) {
				model.addAttribute("message",uva.getMessage());
			}catch(SQLException sqle) {
					model.addAttribute("message",sqle.getMessage());		
			}catch(Exception e) {
				model.addAttribute("message",e.getMessage());
			}
		return new ModelAndView("login");
				
	}
	 
	 @PostMapping(value="adminloginsubmit")
	public ModelAndView adminLoginSubmit(Model  model, @ModelAttribute("user") User admin) 
			{
		
		
		try {
			userService.validation(admin);
			admin.setRoleId(1);
			admin= userService.authorize(admin); 
			model.addAttribute("currentUser",admin);
			return new ModelAndView("redirect:/user/dashboard");		
		}catch(UserValidationException uva) {
			model.addAttribute("message",uva.getMessage());		
		}catch(SQLException sqle) {
			model.addAttribute("message",sqle.getMessage());				
		}catch(Exception e) {
			model.addAttribute("message",e.getMessage());		
		}
		return new ModelAndView("/adminscreen/adminlogin");
							
	}
	 @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	 public ModelAndView showUser(Model  model, @PathVariable("id") int id) {
		 try {
			User user = userService.findById(id);
			model.addAttribute("user",user);
		} catch (UserValidationException e) {
			e.printStackTrace();
		}
		 return new ModelAndView("/admin/users/edit");
	 
	 }
	 @RequestMapping(value="/logout", method = RequestMethod.GET)
	 public ModelAndView logout( Model  model,WebRequest request) {
		 request.removeAttribute("sessionAttr", WebRequest.SCOPE_SESSION);
		 model.addAttribute("currentUser",setUpUserForm());
		return new ModelAndView("redirect:/login/");
	 }

}