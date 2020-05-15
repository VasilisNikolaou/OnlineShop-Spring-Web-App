package onlineshop.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import onlineshop.model.UserPrincipal;
import onlineshop.model.Order;

@Controller
@RequestMapping("/order/current")
public class OrderController {
	
	@ModelAttribute("order")
	public Order order() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
		
		Order order = new Order();
		order.setUser(userPrincipal.getUser());
		
		return order;
	}
	
	@GetMapping
	public String showOrderForm() {
		return "order";
	}
}