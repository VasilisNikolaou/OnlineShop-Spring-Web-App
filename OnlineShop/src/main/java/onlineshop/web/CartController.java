package onlineshop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

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
}
