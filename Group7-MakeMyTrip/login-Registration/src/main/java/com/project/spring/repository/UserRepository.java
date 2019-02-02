package com.project.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.spring.model.User;

public interface UserRepository extends MongoRepository<User, String>{

	public User findByuserName(String userName);
	public User findBymobileNo(Long mobileNo);
	//public ArrayList<User> searchAll();
	
	
}
