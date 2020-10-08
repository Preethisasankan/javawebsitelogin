package javaprojectgit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {
	@RequestMapping("login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	@RequestMapping("loginSubmit")
	public ModelAndView loginSubmit(@RequestParam("userName") String userName,@RequestParam("userPassword") String userPassword) {
		
		return new ModelAndView("home");
	}


}