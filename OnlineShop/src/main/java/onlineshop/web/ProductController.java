package onlineshop.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import onlineshop.model.Category;
import onlineshop.model.Product;
import onlineshop.model.Category.Type;
import onlineshop.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
	
    private ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@ModelAttribute
	public void loadProducts(Model model) {
        List<Product> products = new ArrayList<>();
		
		productService.findAll().forEach(product -> products.add(product));
		
		Type[] types = Category.Type.values();
		
		for(Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(products, type));
		}
	}
	
	@GetMapping
	public String showProducts() {
		
		return "products";
				
	}
	
	private List<Product> filterByType(List<Product> products, Type type) {
		return products.stream()
		                 .filter(product -> product.getCategory().getType().toString().equals(type.toString()))
		                 .collect(Collectors.toList());
	}

}
