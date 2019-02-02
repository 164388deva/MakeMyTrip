package com.project.MakeMytripAPI.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Passenger")
public class Passenger {

	@Id
	private String email;
     private String passengerName; 
      private String password;
	
	public Passenger() {
		super();
	}


	public Passenger( String passengerName, String email, String password) {
		super();

		this.passengerName = passengerName;
		this.email = email;
		this.password = password;

	}
	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "Passenger [email=" + email + ", passengerName=" + passengerName + ", password=" + password + "]";
	}

}
