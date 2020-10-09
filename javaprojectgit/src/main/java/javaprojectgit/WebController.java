package javaprojectgit;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javaprojectgit.model.UserModel;
import javaprojectgit.services.UserService;

@Controller
public class WebController {
	@Autowired
	UserService userService;

	@RequestMapping("login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	@RequestMapping("/profile")
	public ModelAndView profile() {
		return new ModelAndView("profile");
	}
	@RequestMapping("loginSubmit")
	public ModelAndView loginSubmit(@RequestParam("userName") String userName,@RequestParam("userPassword") String userPassword) {
		UserModel userModel = new UserModel();
		userModel.setUserName(userName);
		userModel.setUserPassword(userPassword);
		if(userService.validation(userModel)) {
			ModelAndView mv= new ModelAndView("/profile");
			mv.addObject("Name",userModel.getUserName());
			return mv;
		}else {
			ModelAndView mv= new ModelAndView("login");
			mv.addObject( "message","Please enter Username & Password");
			return mv;
		}
		
	}


}