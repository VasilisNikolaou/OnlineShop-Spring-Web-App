package onlineshop.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import onlineshop.model.UserPrincipal;
import onlineshop.model.Cart;
import onlineshop.model.OrderItem;
import onlineshop.model.User;
import onlineshop.data.CartRepository;
import onlineshop.data.UserRepository;
import onlineshop.model.Order;

@Controller
@RequestMapping("/order/current")
@SessionAttributes("cart")
public class OrderController {
	
	private CartRepository cartRepo;
	private UserRepository userRepo;
	
	@Autowired
	public OrderController(CartRepository cartRepo, UserRepository userRepo) {
		this.cartRepo = cartRepo;
		this.userRepo = userRepo;
	}
	
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
	
	@PostMapping
	public String processOrderForm(@Valid Order order, Errors errors, @ModelAttribute("cart") Cart cart, SessionStatus sessionStatus) {
		
		if(errors.hasErrors())
			return "orderForm";
		
		List<OrderItem> orderItems = cart.getItems().stream()
		                                            .map(item -> new OrderItem(item.getQuantity(), item.getProduct()))
		                                            .collect(Collectors.toList());
		
		orderItems.forEach(orderItem -> order.addOrderItem(orderItem));
		order.setTotal_price(cart.getTotalPrice());
		
	    Cart savedCart = cartRepo.save(cart);
		
		User user = order.getUser();
		user.addOrder(order);
		user.setCart(savedCart);
		
		userRepo.save(user);
		
		sessionStatus.isComplete();
		
		return "redirect:/home";
		
	}
}