package javaprojectgit.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javaprojectgit.model.ProductCategory;
import javaprojectgit.model.Products;


public class ProductService {
	@Autowired
	private JdbcTemplate template;
	/**
	 * To list all products
	 * @return
	 */
	public List<Products> findAllproducts(){
		return template.query("SELECT * FROM products", new ProductMapper());
	}
	/**
	 * To save product
	 * @param product
	 */
	public void saveProduct(Products product) {
		String sql;
		if(product.getId()>0) {
			sql = "UPDATE products SET name=?, sku=?, price=?, "
                    + "description=?, category=?,stock=?,status=?,image=? WHERE id=?";
			template.update(sql, product.getName(), product.getSku(),product.getPrice(), product.getDescription(),product.getCategory(), product.getStock(), product.isStatus(),product.getImage(),product.getId());	
		}else {
		 sql = "INSERT INTO products (name, sku, price, description,category,stock,image)"
                + " VALUES (?, ?, ?, ?,?,?)";
		 template.update(sql, product.getName(), product.getSku(),product.getPrice(), product.getDescription(),product.getCategory(), product.getStock(),product.getImage());
		}
		
		
	}
	/**
	 * list all product category
	 */
	public List<ProductCategory> findAllCategory() {
	    String sql = "SELECT * FROM product_category";
	    List<ProductCategory> listContact = template.query(sql, new RowMapper<ProductCategory>() {
	 
	        @Override
	        public ProductCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	ProductCategory pCategory = new ProductCategory();
	 
	        	pCategory.setId(rs.getInt("id"));
	        	pCategory.setName(rs.getString("name")); 
	            return pCategory;
	        }
	 
	    });
	 
	    return listContact;
	}
	/**
	 * find the product by id
	 * @param id
	 * @return
	 */
	public Products findProductById(Integer id) {
		 List<Products> products=template.query("SELECT * FROM products where id='"+id+"'", new ProductMapper());
		 if(products.size()>0){
				return products.get(0);
			}
		 return null;
	}
	
}
