package com.expense.feign;



import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.expense.Dto.UserDto;




@FeignClient(name="ExpenseUser")
public interface UserServiceFeign {
	
	
	@GetMapping("user/getUser/{username}")
	 @Consumes(MediaType.APPLICATION_JSON)
	public UserDto getUsersMst(@PathVariable("username")String username);

}
