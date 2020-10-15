package javaprojectgit.services;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;



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
	     System.out.println(b);
	     if(!b) {
	    	 throw new UserValidationException("Please enter username in email format");
	     }
		return (user.getUsername() != "" && user.getPassword() != "" && matcher.matches());
	}
	
	public User authorize(User user) throws UserValidationException{
		String sql="SELECT us.*, r.role_id FROM user_roles r JOIN user us ON us.id = r.user_id WHERE us.username ='"+user.getUsername()+"' and us.password='"+user.getPassword()+"' and r.role_id='"+user.getRoleId()+"'";
		List<User> users=template.query(sql, 
				new UserMapper());
		
		if(users.size()>0){
			return users.get(0);
		}else {
			throw new UserValidationException("Username or password is incorrect");
		}
	}
	public List<User> getUser(){
		return template.query("select * from user", new UserMapper());
	}

}
