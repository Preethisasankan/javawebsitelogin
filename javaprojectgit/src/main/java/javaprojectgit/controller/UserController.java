package javaprojectgit.controller;

import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javaprojectgit.exception.UserValidationException;
import javaprojectgit.model.User;
import javaprojectgit.services.UserService;

@Controller
@RequestMapping(value="/user")
@SessionAttributes("currentUser")
public class UserController {
	@Autowired 
	UserService userService;
	
	 @GetMapping("/profile")
		public ModelAndView profile(Model model, @SessionAttribute("currentUser") User currentUser) {
			if(currentUser != null) {
				model.addAttribute("Name",currentUser.getName());
				return new ModelAndView("profile");
			}else {
				return new ModelAndView("redirect:/login");
			}
		}
	 
	 @GetMapping("/dashboard")
		public ModelAndView adminDashboard(Model model,  @SessionAttribute("currentUser") User currentUser ) {
		  
			 	if(userService.isAdmin(currentUser)) {
				model.addAttribute("currentUser",currentUser);
				return new ModelAndView("/adminscreen/dashboard");
			 	}
				 
		 return new ModelAndView("redirect:/admin");
		}
	 
	 @RequestMapping(value = "/", method = RequestMethod.GET)
	 public ModelAndView ListUser(Model  model, @SessionAttribute("currentUser") User currentUser) {
		 if(userService.isAdmin(currentUser)) {
		 List<User> listCustomer=userService.getUser();
		 model.addAttribute("listCustomer",listCustomer);
		 return new ModelAndView("/adminscreen/users/list");
		 }
		 return new ModelAndView("redirect:/admin");
	 }
	 
	 @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	 public ModelAndView showUser(Model  model, @PathVariable("id") int id, @SessionAttribute("currentUser") User currentUser) {
		 try {
			 if(userService.isAdmin(currentUser)) {
			User user = userService.findById(id);
			model.addAttribute("user",user);
			 }
		} catch (UserValidationException uva) {
			model.addAttribute("message",uva.getMessage());
		}
		 return new ModelAndView("/adminscreen/users/edit");
	 
	 }
	 
	 @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	 public ModelAndView update(Model  model, @PathVariable("id") int id) {
		 try {

			 userService.deactivateActivate(id);
			
		} catch (UserValidationException uve) {
			model.addAttribute("message",uve.getMessage());
		}catch (SQLException sqle) {
			model.addAttribute("message",sqle.getMessage());
		}catch(Exception e) {
			model.addAttribute("message",e.getMessage());
		}
		 return new ModelAndView("redirect:/user/");
	 
	 }

}
