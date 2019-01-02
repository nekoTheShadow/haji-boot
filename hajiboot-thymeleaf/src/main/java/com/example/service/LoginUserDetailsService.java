package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.repository.UserRepository;

@Service
public class LoginUserDetailsService implements UserDetailsService{
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = userRepository.findById(username);
		user.orElseThrow(() -> new UsernameNotFoundException("The request user is not found"));
		return new LoginUserDetails(user.get());
	}

}
