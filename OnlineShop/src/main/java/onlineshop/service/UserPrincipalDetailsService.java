package onlineshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import onlineshop.model.User;
import onlineshop.security.UserPrincipal;
import onlineshop.data.UserRepository;

@Service
public class UserPrincipalDetailsService implements UserDetailsService{

    private UserRepository userRepo;
	
	
	@Autowired
	public UserPrincipalDetailsService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user =  userRepo.findByUsername(username);
		
		if(user.isPresent()) {
			return new UserPrincipal(user.get());
		}
		
		throw new UsernameNotFoundException("User '" + username + "' not found");
	}

}
