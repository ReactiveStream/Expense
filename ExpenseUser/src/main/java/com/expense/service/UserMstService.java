package com.expense.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.dto.UserDto;
import com.expense.entites.UsersMst;
import com.expense.repositories.UsersMstRepository;

@Service
public class UserMstService {

	@Autowired
	private UsersMstRepository usersMstRepository;
	
	public UserDto getUsersMst(String userName) {
		System.out.println("1");
		UsersMst usersMst =usersMstRepository.getUser(userName);
		UserDto userDto=new UserDto();
		if(usersMst!=null) {
			BeanUtils.copyProperties(usersMst, userDto);
		}
		return userDto;
	}
	
	
}
