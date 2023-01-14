package com.amrita.menu.service.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.amrita.menu.service.aggregator.EmailService;
import com.amrita.menu.service.model.CanteenAllMenu;
import com.amrita.menu.service.model.CanteenMenu;
import com.amrita.menu.service.model.ERole;
import com.amrita.menu.service.model.EmailDetails;
import com.amrita.menu.service.model.EmailRequest;
import com.amrita.menu.service.model.ITCanteenMenu;
import com.amrita.menu.service.model.LoginRequest;
import com.amrita.menu.service.model.MainCanteenMenu;
import com.amrita.menu.service.model.MbaCanteenMenu;
import com.amrita.menu.service.model.MessMenu;
import com.amrita.menu.service.model.OtpRequest;
import com.amrita.menu.service.model.Role;
import com.amrita.menu.service.model.SignupRequest;
import com.amrita.menu.service.model.UpdatePasswordRequest;
import com.amrita.menu.service.model.User;
import com.amrita.menu.service.repository.ITCanteenRepository;
import com.amrita.menu.service.repository.MainCanteenRepository;
import com.amrita.menu.service.repository.MbaCanteenRepository;
import com.amrita.menu.service.repository.MessRepository;
import com.amrita.menu.service.repository.RoleRepository;
import com.amrita.menu.service.repository.UserRepository;
import com.amrita.menu.service.security.jwt.JwtUtils;
import com.amrita.menu.service.security.services.UserDetailsImpl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertNotNull;
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters=false)
@SpringBootTest
 class MenuManagementControllerTest {
	

	@InjectMocks
	MenuManagementController menuManagementController;
	
	 
	 @Mock
	 AuthenticationManager authenticationManager;
	 
	 @Mock
	 private JwtUtils jwtUtil;
	 
	 @Mock
	 UserRepository userRepository;
	 
	 @Mock
	 EmailService emailService;
	 
	 
	 @Mock
	 RoleRepository roleRepository;
	 
	 @Mock
	 PasswordEncoder encoder;
	 
	
	  
	 @Mock ITCanteenRepository itCanteenRepository;
	  
	 @Mock MbaCanteenRepository mbaCanteenRepository;
	  
	 @Mock MainCanteenRepository mainCanteenRepository;
	  
	 @Mock MessRepository messRepository;
	 
	 
	 

	
	@Test
	public void authenticateUserTest() throws Exception
	{
		
		LoginRequest lr=new LoginRequest();
		
		lr.setPassword("darshan993342");
		lr.setUsername("Darshanytkmff");
		
		Authentication authentication = Mockito.mock(Authentication.class);
		
	
	    when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication); 
	    ArrayList<GrantedAuthority> res=new ArrayList<>();
	    UserDetailsImpl ur=new UserDetailsImpl(12123132l,"darssdasda","reweds@gmail.com","123123asdasdads","dasdasdasdads",res);
	    
	    when(authentication.getPrincipal()).thenReturn(ur);
	    
	    HttpServletResponse response=null;
	    
	    ResponseEntity<?> result=menuManagementController.authenticateUser(lr,response);
		
	}
	
	
	@Test
	void forgotUserPasswordTest() throws Exception
	{
		
		EmailRequest er=new EmailRequest();
		
		er.setEmailId("darshan@gmail.com");
				
		
		User user = new User("darshahnsasads","dfsdfs23423sdsdf","darshan@sadasda","sdasdasdasdasd","werwerwer234234","qweqweqweeqw");
		
		
		

	    when(userRepository.findByEmail(Mockito.anyString())).thenReturn(user); 
	    
	    when(userRepository.save(user)).thenReturn(user); 
	
		
	    
	    
	    when(emailService.sendSimpleMail(any(EmailDetails.class))).thenReturn(Mockito.anyString());
	    
	    ResponseEntity<?> result=menuManagementController.forgotUserPassword(er);
	    
	    assertTrue(result.getStatusCode().is2xxSuccessful());
		
		
		
	}
	
	
	@Test
	 void verifyUserOtpPositiveTest() throws Exception
	{
		
		OtpRequest or=new OtpRequest();
		
		or.setUserOtp("99948");
		or.setEmailId("darshan@gmail.com");
				
		User user = new User("darshahnsasads","dfsdfs23423sdsdf","darshan@gmail.com","99948","werwerwer234234","qweqweqweeqw");
		
	    when(userRepository.findByEmail(Mockito.anyString())).thenReturn(user); 
	    
	        
	    ResponseEntity<?> result=menuManagementController.verifyUserOtp(or);
	    
	    assertTrue(result.getStatusCode().is2xxSuccessful());
		
		
		
	}
	
	@Test
	 void verifyUserOtpNegativeTest() throws Exception
	{
		
		OtpRequest or=new OtpRequest();
		
		or.setUserOtp("99941");
		or.setEmailId("darshan@gmail.com");
				
		User user = new User("darshahnsasads","dfsdfs23423sdsdf","darshan@gmail.com","99948","werwerwer234234","qweqweqweeqw");
		
	    when(userRepository.findByEmail(Mockito.anyString())).thenReturn(user); 
	    
	        
	    ResponseEntity<?> result=menuManagementController.verifyUserOtp(or);
	    
	    assertTrue(result.getStatusCode().is4xxClientError());
		
		
		
	}
	
	
	@Test
	 void getCanteenMenuOneTest() throws Exception
	{
		
		List<MainCanteenMenu> menu=new ArrayList<>();
		
		
		menu.add(new MainCanteenMenu("gobi",10,10,"specials","main_canteen"));
		
				
		
	    when(mainCanteenRepository.findAll()).thenReturn(menu); 
	    
	        
	    CanteenMenu result=menuManagementController.getCanteenMenu(1);
	    
	    assertTrue(result!=null);
		
		
		
	}
	
	@Test
	public void getCanteenMenuTwoTest() throws Exception
	{
		
		List<MainCanteenMenu> menu=new ArrayList<>();
		
		
		menu.add(new MainCanteenMenu("gobi",10,10,"specials","main_canteen"));
		
				
		
		lenient().when(mainCanteenRepository.findAll()).thenReturn(menu); 
	    
	        
	    CanteenMenu result=menuManagementController.getCanteenMenu(2);
	    
	    assertTrue(result!=null);
		
		
		
	}
	
	@Test
	 void getCanteenMenuThreeTest() throws Exception
	{
		
		List<MainCanteenMenu> menu=new ArrayList<>();
		
		
		menu.add(new MainCanteenMenu("gobi",10,10,"specials","main_canteen"));
		
				
		
		lenient().when(mainCanteenRepository.findAll()).thenReturn(menu); 
	    
	        
	    CanteenMenu result=menuManagementController.getCanteenMenu(3);
	    
	    assertTrue(result!=null);
		
		
		
	}
	
	@Test
	 void getCanteenMenuFourTest() throws Exception
	{
		
		List<MainCanteenMenu> menu=new ArrayList<>();
		
		
		menu.add(new MainCanteenMenu("gobi",10,10,"specials","main_canteen"));
		
				
		
		lenient().when(mainCanteenRepository.findAll()).thenReturn(menu); 
	    
	        
	    CanteenMenu result=menuManagementController.getCanteenMenu(4);
	    
	    assertTrue(result!=null);
		
		
		
	}
	
	
	@Test
	 void registerUserRollExistsTest() throws Exception
	{
		
		SignupRequest srr=new SignupRequest();
		
		srr.setEmail("d@gmail.com");
		
		srr.setMobileNo("9994872250");
		
		srr.setPassword("2darshan");
		
		Set<String> role=new HashSet<>();
		
		role.add("admin");
		
		role.add("mod");
		
		role.add("user");
		
		srr.setRole(role);
		
		srr.setRollNo("cb.en.p2cs22022");
		
		srr.setUsername("darshan99");
		
				
		
	    when(userRepository.existsByRollNo(Mockito.anyString())).thenReturn(true); 
	    
	        
	    ResponseEntity<?> result=menuManagementController.registerUser(srr);
	    
	    assertTrue(result.getStatusCode().is4xxClientError());
		
		
		
	}
	
	
	@Test
	 void registerUserEmailExistsTest() throws Exception
	{
		
		SignupRequest srr=new SignupRequest();
		
		srr.setEmail("d@gmail.com");
		
		srr.setMobileNo("9994872250");
		
		srr.setPassword("2darshan");
		
		Set<String> role=new HashSet<>();
		
		role.add("admin");
		
		role.add("mod");
		
		role.add("user");
		
		srr.setRole(role);
		
		srr.setRollNo("cb.en.p2cs22022");
		
		srr.setUsername("darshan99");
		
				
		
	    when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(true); 
	    
	        
	    ResponseEntity<?> result=menuManagementController.registerUser(srr);
	    
	    assertTrue(result.getStatusCode().is4xxClientError());
		
		
		
	}
	
	
	@Test
	 void registerUserRoleNullTest() throws Exception
	{
		
		SignupRequest srr=new SignupRequest();
		
		srr.setEmail("d@gmail.com");
		
		srr.setMobileNo("9994872250");
		
		srr.setPassword("2darshan");
		
		
		
		srr.setRole(null);
		
		srr.setRollNo("cb.en.p2cs22022");
		
		srr.setUsername("darshan99");
		
		Optional<Role> rr=Optional.of(new Role(ERole.ROLE_USER));
		
		when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(rr);
				    
	    ResponseEntity<?> result=menuManagementController.registerUser(srr);
	    
	    assertTrue(result.getStatusCode().is2xxSuccessful());
		
		
		
	}
	
	
	@Test
	 void registerUserRoleTest() throws Exception
	{
		
		SignupRequest srr=new SignupRequest();
		
		srr.setEmail("d@gmail.com");
		
		srr.setMobileNo("9994872250");
		
		srr.setPassword("2darshan");
		
		Set<String> role=new HashSet<>();
		
		role.add("admin");
		
		role.add("mod");
		
		role.add("user");
		
		srr.setRole(role);
		
		srr.setRollNo("cb.en.p2cs22022");
		
		srr.setUsername("darshan99");
		
		Optional<Role> rr=Optional.of(new Role(ERole.ROLE_USER));
		
		
				
		
	    when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(false);
	    when(userRepository.existsByRollNo(Mockito.anyString())).thenReturn(false);
	    
	    when(roleRepository.findByName(ERole.ROLE_ADMIN)).thenReturn(rr);
	    
	    when(roleRepository.findByName(ERole.ROLE_MODERATOR)).thenReturn(rr);
	    
	    when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(rr);
	        
	    ResponseEntity<?> result=menuManagementController.registerUser(srr);
	    
	    assertTrue(result.getStatusCode().is2xxSuccessful());
		
		
		
	}
	
	
	@Test
	 void getAllCanteenMenuTest() throws Exception
	{
		
		List<MainCanteenMenu> menu1=new ArrayList<>();
		
		
		menu1.add(new MainCanteenMenu("gobi",10,10,"specials","main_canteen"));
		
		
		List<MbaCanteenMenu> menu2=new ArrayList<>();
		
		
		menu2.add(new MbaCanteenMenu("gobi",10,10,"specials","main_canteen"));
		
		List<ITCanteenMenu> menu3=new ArrayList<>();
		
		
		menu3.add(new ITCanteenMenu("gobi",10,10,"specials","main_canteen"));
		
		
		List<MessMenu> menu4=new ArrayList<>();
		
		
		menu4.add(new MessMenu("gobi","Monday"));
		
		
				
		
		lenient().when(mainCanteenRepository.findAll()).thenReturn(menu1);
		
		lenient().when(mbaCanteenRepository.findAll()).thenReturn(menu2);
		
		lenient().when(itCanteenRepository.findAll()).thenReturn(menu3);
		
		lenient().when(messRepository.findAll()).thenReturn(menu4);
		        
		CanteenAllMenu result=menuManagementController.getAllCanteenMenu();
	    
	    assertNotNull(result);
		
		
		
	}
	
	@Test
	 void updatePasswordTest() throws Exception
		{
		
		UpdatePasswordRequest ur=new UpdatePasswordRequest();
		
		ur.setEmailId("darshan@gmail.com");
		ur.setUserPassword("2dagdajjjfme");
			
		User user = new User("darshahnsasads","dfsdfs23423sdsdf","darshan@gmail.com","99948","werwerwer234234","qweqweqweeqw");
		
	    when(userRepository.findByEmail(Mockito.anyString())).thenReturn(user);
			        
	    when(userRepository.save(user)).thenReturn(user);
	    
	    ResponseEntity<?> result=menuManagementController.updatePassword(ur);
		    
	    assertTrue(result.getStatusCode().is2xxSuccessful());
			
			
			
		}
	

}
