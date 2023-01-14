package com.amrita.menu.service.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
 class SignupRequestTest {
	
	
	SignupRequest sr=new SignupRequest();
	
	@Test
	void getRollTest()
	{
		sr.setRollNo("123432");
		
		assertEquals("123432",sr.getRollNo());
	}
	
	@Test
	void getMobileNoTest()
	{
		sr.setMobileNo("123432");
		
		assertEquals("123432",sr.getMobileNo());
	}
	
	@Test
	void getUsernameTest()
	{
		sr.setUsername("Darshan");
		
		assertEquals("Darshan",sr.getUsername());
	}
	
	@Test
	void getEmailTest()
	{
		sr.setEmail("Darshan@gmail.com");
		
		assertEquals("Darshan@gmail.com",sr.getEmail());
	}
	
	@Test
	void getPasswordTest()
	{
		sr.setPassword("Darshan@gmail.com");
		
		assertEquals("Darshan@gmail.com",sr.getPassword());
	}
	
	@Test
	void getRoleTest()
	{
		Set<String> hs=new HashSet<>();
		
		hs.add("ted");
		sr.setRole(hs);
		
		assertEquals(1,sr.getRole().size());
	}
	
	
	
	

}
