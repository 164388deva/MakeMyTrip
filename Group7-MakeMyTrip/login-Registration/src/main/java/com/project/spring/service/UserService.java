package com.project.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.spring.model.User;
import com.project.spring.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public String addUser(User user) {
		if(userRepository.existsById(user.getEmailId())) {
			return "Sorry! This emailId already exists in our records. Please try again with a new ID";
		}
		if(user.getPassword().equals(user.getPasswordConfirm())) {
			 userRepository.save(user);
			 return "Congratulations!! you have been added!!";
			}
		else {
			return "Sorry! the password and the confirm-password does not match! ";
		}

	}
	
	public Optional<User> getUser(String emailId) {
		return userRepository.findById(emailId);
	}
	
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	public User getUserByMobileNo(Long mobileNo) {
		return userRepository.findBymobileNo(mobileNo);
	}
	
	public User getUserByName(String userName) {
		return userRepository.findByuserName(userName);
	}
	
	public void deleteUser(String emailId) {
		userRepository.deleteById(emailId);	
	}

	public boolean isPresent(String emailId) {
		return userRepository.existsById(emailId);
	}
	
	public List<User> searchAll() {
		List<User> userList = userRepository.findAll();
		return userList;

	}
	
	public String validatate(String emailId, String password)
	{
		List<User> list= getAllUser();
		
		for(User user:list) {
		if(emailId.equals(user.getEmailId() )&& password.equals(user.getPassword())) {
		return "login Successful!!!";
		}
		}
		return "login successful";		
	}
	}
