package com.amrita.menu.service.model;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class SignupRequestTest {
	
	
	SignupRequest sr=new SignupRequest();
	
	@Test
	void getRollTest()
	{
		sr.setRollNo("123432");
		
		assertTrue(sr.getRollNo().equals("123432"));
	}
	
	@Test
	void getMobileNoTest()
	{
		sr.setMobileNo("123432");
		
		assertTrue(sr.getMobileNo().equals("123432"));
	}
	
	@Test
	void getUsernameTest()
	{
		sr.setUsername("Darshan");
		
		assertTrue(sr.getUsername().equals("Darshan"));
	}
	
	@Test
	void getEmailTest()
	{
		sr.setEmail("Darshan@gmail.com");
		
		assertTrue(sr.getEmail().equals("Darshan@gmail.com"));
	}
	
	@Test
	void getPasswordTest()
	{
		sr.setPassword("Darshan@gmail.com");
		
		assertTrue(sr.getPassword().equals("Darshan@gmail.com"));
	}
	
	@Test
	void getRoleTest()
	{
		Set<String> hs=new HashSet<>();
		
		hs.add("ted");
		sr.setRole(hs);
		
		assertTrue(sr.getRole().size()==1);
	}
	
	
	
	

}
