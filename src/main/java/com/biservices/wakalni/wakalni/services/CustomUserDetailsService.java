package com.biservices.wakalni.wakalni.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;*/
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.biservices.wakalni.wakalni.persistence.entities.user.PlatformUser;
import com.biservices.wakalni.wakalni.persistence.repositories.UserRepository;
@Service
//public class CustomUserDetailsService implements UserDetailsService   {
public class CustomUserDetailsService {
	
	@Autowired
	private UserRepository userRepo;

	/*@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		PlatformUser user= userRepo.findByUsername(username);
		  if (user == null) {
		      throw new UsernameNotFoundException("User " + username + " not found");
		    }
		    UserBuilder builder = null;
		    builder = org.springframework.security.core.userdetails.User.withUsername(username);
		    builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
		    builder.roles("ADMIN");
		    return builder.build();
	}*/
	
	/*public PlatformUser loadUserByUsernameAndPassword(String username, String password) throws UsernameNotFoundException {
		return userRepo.findByUsernameAndPassword(username, password);
	}*/
	
	public List<PlatformUser >findAllUsers () {
		return userRepo.findAll();
	}
	
	public PlatformUser saveNewUser(PlatformUser user) {
		 user = userRepo.save(user);
		return user;
	}
	
	public PlatformUser getUserById(Long userId) {
		 
		return userRepo.findOne(userId);
	}

}
