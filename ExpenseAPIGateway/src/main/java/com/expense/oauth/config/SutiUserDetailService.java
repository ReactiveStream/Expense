package com.expense.oauth.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.expense.Dto.UserDto;
import com.expense.feign.UserServiceFeign;


@Component
public class SutiUserDetailService implements UserDetailsService {

	@Autowired
	private UserServiceFeign userServiceFeign;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDto userdto=userServiceFeign.getUsersMst(username);
		System.out.println(userdto.getPassword());
		UserDetails userDetails=new User(userdto.getUserName(),passwordEncoder.encode(userdto.getPassword()), Collections.singletonList(new SimpleGrantedAuthority("user")));
		return userDetails;
	}

}
