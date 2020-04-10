package onlineshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import onlineshop.model.Product;
import onlineshop.data.ProductRepository;

@Service
@CacheConfig(cacheNames={"products"})
public class ProductService {
	
	private ProductRepository productRepo;
	private List<Product> products = new ArrayList<>();
	
	
	@Autowired
	public ProductService(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}
	
	@Cacheable
	public List<Product> findAll() {
		this.products = productRepo.findAll();
		
		return this.products;
	}

}
