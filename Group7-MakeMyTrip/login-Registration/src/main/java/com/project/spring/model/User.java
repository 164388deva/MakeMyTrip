package com.project.spring.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
public class User {
	
	    @Id
	    private String userName;
	    private String emailId;
	    private Long mobileNo;
	    private String password;
	    private String passwordConfirm;
		public User() {
			super();
			// TODO Auto-generated constructor stub
		}
		public User(String userName, String emailId, Long mobileNo, String password, String passwordConfirm) {
			super();
			this.userName = userName;
			this.emailId = emailId;
			this.mobileNo = mobileNo;
			this.password = password;
			this.passwordConfirm = passwordConfirm;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getEmailId() {
			return emailId;
		}
		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}
		public Long getMobileNo() {
			return mobileNo;
		}
		public void setMobileNo(Long mobileNo) {
			this.mobileNo = mobileNo;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getPasswordConfirm() {
			return passwordConfirm;
		}
		public void setPasswordConfirm(String passwordConfirm) {
			this.passwordConfirm = passwordConfirm;
		}
		@Override
		public String toString() {
			return "User [userName=" + userName + ", emailId=" + emailId + ", mobileNo=" + mobileNo + ", password="
					+ password + ", passwordConfirm=" + passwordConfirm + "]";
		}
	    
	   
	    
				
	    

}
