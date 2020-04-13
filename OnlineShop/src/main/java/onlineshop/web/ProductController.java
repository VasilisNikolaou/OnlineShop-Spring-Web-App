package onlineshop.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import onlineshop.model.Cart;
import onlineshop.model.Category;
import onlineshop.model.Item;
import onlineshop.model.Product;
import onlineshop.model.Category.Type;
import onlineshop.service.ProductService;

@Controller
@RequestMapping("/products")
@SessionAttributes("cart")
public class ProductController {
	
    private ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@ModelAttribute("cart")
	public Cart cart() {
		return new Cart();
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
	
	@PostMapping("/{id}")
	public String processProducts(@PathVariable Long id, @ModelAttribute("cart") Cart cart) {
		
		Optional<Product> product = productService.findById(id);
		
		if(product.isPresent()) {
			Item item = new Item(product.get());
			
			cart.getItems().stream()
					            .filter(i -> i.equals(item))
					            .findFirst()
					            .ifPresentOrElse(Item::updateQuantity, () -> {
					         	    item.setQuantityToOne();
					         	    cart.addItem(item);
					            });
			
			cart.calculateTotalPrice();
		}
		
		return "redirect:/cart";
	}
	
	private List<Product> filterByType(List<Product> products, Type type) {
		return products.stream()
		                 .filter(product -> product.getCategory().getType().toString().equals(type.toString()))
		                 .collect(Collectors.toList());
	}

}
