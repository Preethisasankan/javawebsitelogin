package javaprojectgit.services;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;

import javaprojectgit.model.Products;
import javaprojectgit.model.User;


class ProductMapper implements RowMapper<Products> {

	@Override
	public Products mapRow(ResultSet rs, int rowNum) throws SQLException {

		Products products =new Products();
		products.setId(rs.getInt("id"));
		products.setName(rs.getString("name"));
		products.setDescription(rs.getString("description"));
		products.setSku(rs.getString("sku"));
		products.setCategory(rs.getString("category"));
		products.setPrice(rs.getDouble("price"));
		products.setStock(rs.getInt("stock"));
		products.setStatus(rs.getBoolean("status"));
		products.setImage(rs.getString("image"));
		return products;
	}



}
