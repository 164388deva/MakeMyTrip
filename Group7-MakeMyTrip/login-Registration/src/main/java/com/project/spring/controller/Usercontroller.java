package com.project.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.spring.model.User;
import com.project.spring.model.login;
import com.project.spring.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin("http://localhost:4200")
@RestController
public class Usercontroller {

	private static final String REGISTRATION_COLLECTION = "userlist";

	@Autowired
	private UserService userService;

	ArrayList<User> list = new ArrayList<User>();



	@ApiOperation(value="it will return user list")
	@RequestMapping(method = RequestMethod.GET, value = "user/get/{emailId}")
	public Optional<User> getUser(@PathVariable String emailId) {
		return userService.getUser(emailId);
	}

	@ApiOperation(value="it will return all list of user")
	@RequestMapping(method = RequestMethod.GET, value = "user/get")
	public List<User> getAllUser() {
		return userService.getAllUser();
	}

	@ApiOperation(value="it will return user based on mobile number")
	@RequestMapping(method = RequestMethod.GET, value = "user/get/mobileNo/{mobileNo}")
	public User getUserByMobileNo(@PathVariable Long mobileNo) {
		return userService.getUserByMobileNo(mobileNo);
	}

	@ApiOperation(value="it will return user based on name")
	@RequestMapping(method = RequestMethod.GET, value = "user/get/userName/{userName}")
	public User getUserByName(@PathVariable String userName) {
		return userService.getUserByName(userName);
	}

	@ApiOperation(value="it will delete user based on emailId")
	@RequestMapping(method = RequestMethod.DELETE, value = "user/delete/{emailId}")
	public void deleteUser(String emailId) {
		userService.deleteUser(emailId);	
	}

	@ApiOperation(value="add user in User list")
	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public User addUser(@RequestBody  User user) { 
		System.out.println(user);
		userService.addUser(user);
		return user;
	}


	@ApiOperation(value="login user if username and password is correct")
	@RequestMapping(method=RequestMethod.POST, value="/login")
	public boolean validatate(@RequestBody login login) {
		System.out.println(login);
		List<User> users=userService.getAllUser();
		System.out.println(users);
			/*for (User user : users) {
				if(user.getEmailId().equals(login.getEmail()) && user.getPassword().equals(login.getPassword())) {
					
					return "Success!";
				}//if ends here
				
			}//for
		return "failed";*/
		
		for (User user : users) {
			if(user.getEmailId().equals(login.getEmail()) && user.getPassword().equals(login.getPassword())) {
				System.out.println("Success");
				return true;
			}
		}
		System.out.println("Failed");
		return false;

		/*String response=userService.validatate(emailId, password);	
		return response;*/

	}
}