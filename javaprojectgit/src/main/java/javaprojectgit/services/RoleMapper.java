package javaprojectgit.services;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import javaprojectgit.model.Role;
import javaprojectgit.model.User;

public class RoleMapper implements RowMapper<Role> {
	@Override
	public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Role userRole =new Role();
		userRole.setId(rs.getInt("role_id"));		
		return userRole;
	}

}
