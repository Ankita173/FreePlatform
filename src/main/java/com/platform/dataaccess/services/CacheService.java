package com.platform.dataaccess.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.platform.dataaccess.model.Product;
import com.platform.dataaccess.repo.ProductRepository;

@Service
@CacheConfig(cacheNames={"product"})
public class CacheService {
	
	Logger log = LoggerFactory.getLogger(CacheService.class);
	
	@Autowired
	private ProductRepository productRepo;
	
	@Cacheable
	public List<Product> getProduct() {
		log.info("Cache miss");
		return productRepo.findAll();
	}
	
	@CacheEvict(allEntries = true) 
	public List<Product> createProduct(Product product) {
		productRepo.save(product);
		return productRepo.findAll();
	}
	
	@CacheEvict(allEntries = true) 
	public List<Product> updateProduct(String old, Product updated) {
		if (deleteProduct(old)) {
			productRepo.save(updated);
		}
		return productRepo.findAll();
	}
	
	@CacheEvict(allEntries = true) 
	public boolean deleteProduct(String product) {
		List<Product> productList = productRepo.findByName(product);
		if (!CollectionUtils.isEmpty(productList)) {
    		log.info("Product found!");
    		productRepo.deleteAll(productList);
        	return true;
        }
		return false;
	}

}
