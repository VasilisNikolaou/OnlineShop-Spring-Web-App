package onlineshop.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import onlineshop.model.Cart;
import onlineshop.model.Product;
import onlineshop.service.ProductService;


@Controller
@RequestMapping("/cart")
@SessionAttributes("cart")
public class CartController {

	private ProductService productService;
	
	@Autowired
	public CartController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping
	public String showCart() {
		return "cart";
	}
	
	@PostMapping("/remove/{name}")
	public String removeCartItem(@PathVariable("name") String name, @ModelAttribute("cart") Cart cart) {
		  
		  Optional<Product> product = productService.findByName(name);
		
		  if(product.isPresent()) {
			  
			   cart.getItems()
			                  .removeIf(item -> item.getProduct().getId() == product.get().getId());
			   
			   cart.calculateTotalPrice();
			  
		  }
		  
		  return "redirect:/cart";
	}
}
