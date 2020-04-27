package onlineshop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import onlineshop.model.Order;

@Controller
@RequestMapping("/order/current")
public class OrderController {

	@ModelAttribute("order")
	public Order order() {
		return new Order();
	}
	
	@GetMapping
	public String showOrderForm() {
		return "order";
	}
}
