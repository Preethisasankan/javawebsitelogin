package services;

import java.lang.String;

public class UserService {
//	@Autowired
//	UserModel model;
	public String validation(String userName, String userPassword) {
		if(userName != "" && userPassword != "") {
//			model.setUsername(userName);
//			model.setPassword(userPassword);
			return "true";
		}else {
			return "false";
		}
		
	}

}
