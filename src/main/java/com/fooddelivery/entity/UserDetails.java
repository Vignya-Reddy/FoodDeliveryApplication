package com.fooddelivery.entity;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="userinfo1")
public class UserDetails {
	@Id
	@Column(name="userid")
	private int userId;
	
	@Column(name="username")
	private String userName;
	
	@Column(name="password")
	private String password;
	 

	@Column(name="role")
	private String role;


	public UserDetails(int userId, String userName, String password, String role) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}


	public UserDetails()  {
		
	}

	

	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", userName=" + userName + ", password=" + password + ", role=" + role
				+ "]";
	}
	
	
	
	
}
