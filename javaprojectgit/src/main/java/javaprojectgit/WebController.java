package javaprojectgit;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javaprojectgit.model.Login;
import javaprojectgit.model.User;
import javaprojectgit.services.UserService;

@Controller
public class WebController {
	@Autowired
	UserService userService;
	
	@RequestMapping("adminDashboard")
	public ModelAndView adminDashboard(Model model) {
		List<User> listCustomer=userService.getDetails();
		model.addAttribute("listCustomer",listCustomer);
		return new ModelAndView("home");
	}
	@RequestMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	@RequestMapping("/profile")
	public ModelAndView profile() {
		return new ModelAndView("profile");
	}
	@RequestMapping("loginSubmit")
	public ModelAndView loginSubmit(@RequestParam("userName") String userName,@RequestParam("userPassword") String userPassword) {
		Login login = new Login();
		login.setUsername(userName);
		login.setPassword(userPassword);
		if(userService.validation(login)) {
				
			User user= userService.authorize(login);
			if(user != null) {
			ModelAndView mv= new ModelAndView("/profile");
			mv.addObject("Name",user.getName());
			return mv;
			}
		}
		ModelAndView mv= new ModelAndView("login");
		mv.addObject( "message",login.getErrorMessage());
		return mv;
		
		
	}


}