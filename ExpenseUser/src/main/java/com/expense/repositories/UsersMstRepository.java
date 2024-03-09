package com.expense.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.expense.entites.UsersMst;

public interface UsersMstRepository extends JpaRepository<UsersMst, Integer> {
	
	@Query("from UsersMst u where u.userName=:userName")
	UsersMst getUser(@Param("userName") String userName);

}
