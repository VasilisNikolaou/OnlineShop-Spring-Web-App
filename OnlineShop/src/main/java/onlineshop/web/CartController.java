package onlineshop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/cart")
@SessionAttributes("cart")
public class CartController {

	@GetMapping
	public String showCart() {
		return "cart";
	}
}
