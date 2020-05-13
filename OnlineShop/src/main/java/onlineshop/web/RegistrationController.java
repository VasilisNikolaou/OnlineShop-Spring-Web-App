package onlineshop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import onlineshop.model.User;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	
	@ModelAttribute("user")
	public User user() {
		return new User();
	}
	
	@GetMapping
	public String showRegisterForm() {
		return "registration";
	}

}
