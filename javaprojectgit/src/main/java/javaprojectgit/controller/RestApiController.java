package javaprojectgit.controller;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javaprojectgit.model.Products;

import javaprojectgit.services.ProductService;
import javaprojectgit.services.UserService;
@RestController
@RequestMapping(value="/api")
public class RestApiController {
	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;
	 @RequestMapping(value = "/productlist", method = RequestMethod.GET)
	public ResponseEntity<List<Products>> listProducts(Model model, @RequestHeader(value="Authorization") String authorizationHeader) {
		 try {
			String base64encodedString = "Basic " + Base64.getEncoder().encodeToString(
				        "preethi@yopmail.com:pass@123".getBytes("utf-8")); 
			if(base64encodedString.equals(authorizationHeader)) {
				List<Products> products=productService.findAllproducts();
				 if(products.isEmpty()) {
					 return new ResponseEntity<List<Products>>(HttpStatus.NO_CONTENT);
				 }
				return new ResponseEntity<List<Products>>(products, HttpStatus.OK); 
			}
			 return new ResponseEntity<List<Products>>(HttpStatus.UNAUTHORIZED);
			   
		} catch (UnsupportedEncodingException e) {
			 return new ResponseEntity<List<Products>>(HttpStatus.BAD_REQUEST);
		}
		 	
	}
	 @RequestMapping(value = "/products/", method = RequestMethod.POST)
	    public ResponseEntity<Void> createProducts(@RequestBody Products products,    UriComponentsBuilder ucBuilder) {
	        	
	        try {
				String base64encodedString = Base64.getEncoder().encodeToString(
				        "preethi@yopmail.com:pass@123".getBytes("utf-8"));
//				productService.saveProduct(products);
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/products/{id}").buildAndExpand(products.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	    }

}
