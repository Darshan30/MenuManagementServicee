package com.amrita.menu.service.model;

import java.util.List;


public class UserInfoResponse {
	private Long id;
	private String username;
	private String email;
	private String rollNo;
	private List<String> roles;
	private String jwt;

	public UserInfoResponse(Long id, String username, String email, List<String> roles,String jwt,String rollNo) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.jwt=jwt;
		this.rollNo=rollNo;
	}
	
	

	public String getJwt() {
		return jwt;
	}



	public void setJwt(String jwt) {
		this.jwt = jwt;
	}



	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	



	public String getRollNo() {
		return rollNo;
	}



	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}
}
