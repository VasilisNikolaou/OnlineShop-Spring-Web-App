package onlineshop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order/current")
public class OrderController {

	@GetMapping
	public String showOrderForm() {
		return "orderForm";
	}
}
