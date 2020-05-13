package onlineshop.web;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import onlineshop.data.UserRepository;
import onlineshop.model.User;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	private PasswordEncoder passwordEncoder;
	private UserRepository  userRepo;

	public RegistrationController(PasswordEncoder passwordEncoder, UserRepository userRepo) {
		this.passwordEncoder = passwordEncoder;
		this.userRepo = userRepo;
	}
	
	@ModelAttribute("user")
	public User user() {
		return new User();
	}
	
	@GetMapping
	public String showRegisterForm() {
		return "registration";
	}
	
	@PostMapping
	public String processRegistration(@Valid User user, Errors errors, Model model, 
			RedirectAttributes redirectAttributes) {
		
	
		if(errors.hasErrors()) {
			return "registration";
		}
		
		User newUser = new User(user.getUsername(), user.getFirstName(), user.getLastName(),
				                passwordEncoder.encode(user.getPassword()), user.getEmail());
		
		userRepo.save(newUser);
		
		if(model.containsAttribute("cart")) {
			return "redirect:/cart";
		}
		
		redirectAttributes.addFlashAttribute("message", "REGISTRATION SUCCESS");
		
		return "redirect:/register";
	}

}
