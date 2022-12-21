package com.platform.dataaccess.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.platform.dataaccess.model.Product;
import com.platform.dataaccess.services.CacheService;

@RestController
public class DataAccessController {
	
	@Autowired
	private CacheService cacheService;
	
	Logger log = LoggerFactory.getLogger(DataAccessController.class);
    
	@RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> getProducts() {
		log.info("GET /products called");
		List<Product> productsList = cacheService.getProduct();
		log.info("Found {} products", productsList.size());
		return productsList;
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public void createProduct(@RequestBody Product product) {
    	log.info("POST /products called to add product {}", product);
    	cacheService.createProduct(product);
		log.info("Product {} is saved successfully", product);
    }
    
    @RequestMapping(value = "/products", method = RequestMethod.PUT)
    public void updateProduct(@RequestParam(name = "old") String old, @RequestBody Product updated) {
    	log.info("PUT /products called to replace product {} to {}", old, updated);
    	cacheService.updateProduct(old, updated);
		log.info("Product {} is updated successfully to {}", old, updated);
    }
    
    @RequestMapping(value = "/products", method = RequestMethod.DELETE)
    public void deleteProduct(@RequestParam("name") String product) {
    	log.info("DELETE /products called to delete product {}", product);
    	cacheService.deleteProduct(product);
		log.info("Product {} is deleted successfully", product);
    }

}
