package com.expense.controller;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.dto.UserDto;
import com.expense.service.UserMstService;



@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserMstService userMstService;
	
	
	@GetMapping("/getUser/{username}")
	 @Produces(MediaType.APPLICATION_JSON)
	public UserDto getUsersMst(@PathVariable("username")String username) {
		return userMstService.getUsersMst(username);
	}

}
