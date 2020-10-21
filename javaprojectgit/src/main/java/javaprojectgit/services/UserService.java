package javaprojectgit.services;


import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javaprojectgit.exception.UserValidationException;
import javaprojectgit.model.User;

public class UserService {
	@Autowired
	private JdbcTemplate template;
	private Pattern pattern;
    private Matcher matcher;
	
	public Boolean validation(User user) throws UserValidationException {
		 final String EMAIL_PATTERN  = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
		 if(StringUtils.isBlank(user.getUsername())||StringUtils.isBlank(user.getPassword())){
			 throw new UserValidationException("Please enter username& password");
		 }
		 pattern = Pattern.compile(EMAIL_PATTERN);
	     matcher = pattern.matcher(user.getUsername());
	     boolean b=matcher.matches();
	     if(!b) {
	    	 throw new UserValidationException("Please enter username in email format");
	     }
		return (user.getUsername() != "" && user.getPassword() != "" && matcher.matches());
	}
	
	public User authorize(User user) throws UserValidationException,SQLException{
		String sql="SELECT us.*, r.role_id FROM user_roles r JOIN user us ON us.id = r.user_id WHERE us.username ='"+user.getUsername()+"' and us.password='"+user.getPassword()+"' and r.role_id='"+user.getRoleId()+"'";
		List<User> users=template.query(sql, 
				new UserMapper());
		
		if(users.size()>0){
			if(users.get(0).isActive()) {
				return users.get(0);
			}else {
				throw new UserValidationException("Your account is deactivated.Please contact admin");
			}
		}else {
			throw new UserValidationException("Username or password is incorrect");
		}
	}
	public List<User> getUser(){
		return template.query("SELECT us.*, r.role_id FROM user_roles r JOIN user us ON us.id = r.user_id WHERE r.role_id=2", new UserMapper());
	}
	public User findById(Integer id) throws UserValidationException{
		List<User> users=template.query("select * from user where id='"+id+"'", new UserMapper());
		if(users.size()>0){
			return users.get(0);
		}else {
			throw new UserValidationException("Username not exists");
		}
	}
	public void deactivateActivate(Integer id) throws UserValidationException,SQLException{
		User user= this.findById(id);
		if(user != null) {
			boolean status= !user.active;
			template.update("UPDATE user SET active= ? WHERE id =?",status,id);
		}
		
	}
}
