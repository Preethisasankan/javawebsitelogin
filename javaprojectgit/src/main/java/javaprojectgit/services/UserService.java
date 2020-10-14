package javaprojectgit.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javaprojectgit.model.Login;
import javaprojectgit.model.User;

public class UserService {
	@Autowired
	private JdbcTemplate template;

	
	public Boolean validation(Login login) {
		
		 if(login.getUsername() == "" || login.getPassword() == "") {
			 login.setErrorMessage("Please fill username & password");
		 }
		return (login.getUsername() != "" && login.getPassword() != "");
	}
	
	public User authorize(Login login){
		
		List<User> users=template.query("select * from user where username='"+login.getUsername()+"' AND password='"+login.getPassword()+"'", 
				new UserMapper());
		if(users.size()>0){
			return users.get(0);
		}else {
			login.setErrorMessage("Username or password is incorrect");
			return null;
		}
	}
	public List<User> getDetails(){
		return template.query("select * from user", new UserMapper());
	}
}
