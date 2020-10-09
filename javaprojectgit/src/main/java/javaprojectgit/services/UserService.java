package javaprojectgit.services;

import javaprojectgit.model.UserModel;

public class UserService {
	
	public boolean validation(UserModel userModel) {
		return (userModel.getUserName() != "" && userModel.getUserPassword() != "");	
	}

}
