package javaprojectgit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import model.UserModel;
import services.UserService;

@Controller
public class WebController {
	@Autowired
	UserService userService;
	
	@Autowired
	UserModel userModel;
	@RequestMapping("login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	@RequestMapping("loginSubmit")
	public ModelAndView loginSubmit(@RequestParam("userName") String userName,@RequestParam("userPassword") String userPassword) {

//		UserService userService= new UserService();
		
		if(userService.validation(userName,userPassword) == "true") {
//			System.out.println(userService.validation(model));
//			UserModel userModel = new UserModel();
			userModel.setUsername(userName);
			userModel.setPassword(userPassword);
			ModelAndView mv= new ModelAndView("profile");
			mv.addObject("Name",userModel.getUsername());
			return mv;
//			return new ModelAndView("profile");
		}else {
//			System.out.println(userService.validation(model));
			ModelAndView mv= new ModelAndView("login");
			mv.addObject( "message","Please enter Username & Password");
			return mv;
//			return new ModelAndView("login");
		}
		
	}


}