package com.amrita.menu.service.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.amrita.menu.service.repository.UserRepository;
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
import com.amrita.menu.service.model.MessageResponse;
import com.amrita.menu.service.model.OrderList;
import com.amrita.menu.service.model.OrderMenuList;
import com.amrita.menu.service.model.OtpRequest;
import com.amrita.menu.service.model.Role;
import com.amrita.menu.service.model.SaveOrderRequest;
import com.amrita.menu.service.model.SignupRequest;
import com.amrita.menu.service.model.UpdatePasswordRequest;
import com.amrita.menu.service.model.User;
import com.amrita.menu.service.model.UserInfoResponse;
import com.amrita.menu.service.repository.RoleRepository;
import com.amrita.menu.service.security.jwt.JwtUtils;
import com.amrita.menu.service.security.services.UserDetailsImpl;
import com.amrita.menu.service.aggregator.EmailService;
import com.amrita.menu.service.repository.ITCanteenRepository;
import com.amrita.menu.service.repository.MainCanteenRepository;
import com.amrita.menu.service.repository.MbaCanteenRepository;
import com.amrita.menu.service.repository.MessRepository;
import com.amrita.menu.service.repository.OrderRepository;
import com.amrita.menu.service.repository.OrderMenuListRepository;
import org.springframework.security.access.prepost.PreAuthorize;




@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MenuManagementController {
	
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;
  

  @Autowired private EmailService emailService;
  
  @Autowired ITCanteenRepository itCanteenRepository;
  
  @Autowired MbaCanteenRepository mbaCanteenRepository;
  
  @Autowired MainCanteenRepository mainCanteenRepository;
  
  @Autowired MessRepository messRepository;
  
  @Autowired OrderRepository orderRepository;
  
  @Autowired OrderMenuListRepository orderMenuListRepository;
  
  
  

  @PostMapping("/api/auth/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,HttpServletResponse response) {

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    String jwtCookie = jwtUtils.generateJwtCookie(userDetails);

    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());
    
    //response.addHeader("jwt_token",jwtCookie.toString());
    
//    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
//        .body(new UserInfoResponse(userDetails.getId(),
//                                   userDetails.getUsername(),
//                                   userDetails.getEmail(),
//                                   roles));
    
    return ResponseEntity.ok()
            .body(new UserInfoResponse(userDetails.getId(),
                                       userDetails.getUsername(),
                                       userDetails.getEmail(),
                                       roles,jwtCookie,userDetails.getRollNo()));
  }
  
  
  @PostMapping("/api/auth/forgotpassword")
  public ResponseEntity<?> forgotUserPassword(@RequestBody EmailRequest emailRequest) {
	  
	  
	  Random r = new Random( System.currentTimeMillis() );
	    int otp=10000 + r.nextInt(20000);
	    
	
	    
	    User updateUserOtp=userRepository.findByEmail(emailRequest.getEmailId());
	    
	    System.out.println("otp"+otp);
	    
	    updateUserOtp.setUserOtp(String.valueOf(otp));
	    
	    userRepository.save(updateUserOtp);
	    
	    EmailDetails emailDetails=new EmailDetails();
	    
	    emailDetails.setRecipient(emailRequest.getEmailId());
	    emailDetails.setMsgBody(String.valueOf(otp));
	    emailDetails.setSubject("OTP");
	    
	    String status
        = emailService.sendSimpleMail(emailDetails);

	    return ResponseEntity.ok(new MessageResponse(status));
  }
  
  @PostMapping("/api/auth/verifyOtp")
  public ResponseEntity<?> verifyUserOtp(@RequestBody OtpRequest otpRequest) {
	  
	  
	 String dbOtp=userRepository.findByEmail(otpRequest.getEmailId()).getUserOtp();
	 
	 if(dbOtp.equals(otpRequest.getUserOtp()))
	 {
		 return ResponseEntity.ok(new MessageResponse("OTP verified sucessfully"));
	 }
	 
	 return ResponseEntity.badRequest().body(new MessageResponse("OTP mismatch"));
  }
  
  
  @PostMapping("/api/auth/updatePassword")
  public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest) {
	  
	  
	 User userDetails=userRepository.findByEmail(updatePasswordRequest.getEmailId());
	 
	 userDetails.setPassword(encoder.encode(updatePasswordRequest.getUserPassword()));
	 
	 userRepository.save(userDetails);
	 
	 return ResponseEntity.ok(new MessageResponse("Password Updated"));
  }
  
  

  @PostMapping("/api/auth/register")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByRollNo(signUpRequest.getRollNo())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: RollNo already registered!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
                         signUpRequest.getEmail(),
                         encoder.encode(signUpRequest.getPassword()),"",signUpRequest.getRollNo(),signUpRequest.getMobileNo());

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();
    

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "mod":
          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;
        case "user":
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
          
          break;
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  @PostMapping("/api/auth/signout")
  public ResponseEntity<?> logoutUser() {
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body(new MessageResponse("You've been signed out!"));
  }
  
  @GetMapping("/api/auth/getCanteenMenu/{canteenId}")
  @PreAuthorize("hasRole('USER')")
  public CanteenMenu getCanteenMenu(@PathVariable int canteenId)
  {
	  
	  switch(canteenId) {
	  
	  case 1:
	  {
		List<MainCanteenMenu> mainCanteenMenuList= mainCanteenRepository.findAll(); 
		
		CanteenMenu m1=new CanteenMenu();
		m1.setCanteenMenu(mainCanteenMenuList);
		
		return m1;
	  }
	  
	  case 2:
	  {
		List<MbaCanteenMenu> mbaCanteenMenuList= mbaCanteenRepository.findAll(); 
		
		CanteenMenu m2=new CanteenMenu();
		m2.setCanteenMenu(mbaCanteenMenuList);
		
		return m2;
	  }
	  
	  case 3:
	  {
		List<ITCanteenMenu> itCanteenMenuList= itCanteenRepository.findAll(); 
		
		CanteenMenu m3=new CanteenMenu();
		m3.setCanteenMenu(itCanteenMenuList);
		
		return m3;
	  }
	  
	  case 4:
	  {
		List<MessMenu> messMenuList= messRepository.findAll(); 
		
		CanteenMenu m4=new CanteenMenu();
		m4.setCanteenMenu(messMenuList);
		
		return m4;
	  }
	  
	  
	  }
	  
	  return null;

  }
  
  
  @GetMapping("/api/auth/getAllCanteenMenu")
  @PreAuthorize("hasRole('USER')")
  public CanteenAllMenu getAllCanteenMenu()
  {
	  
	  	
	  	CanteenAllMenu canteenAllMenu = new CanteenAllMenu();
	  
		List<MainCanteenMenu> mainCanteenMenuList= mainCanteenRepository.findAll(); 
	
		List<MbaCanteenMenu> mbaCanteenMenuList= mbaCanteenRepository.findAll(); 

		List<ITCanteenMenu> itCanteenMenuList= itCanteenRepository.findAll(); 

		List<MessMenu> messMenuList= messRepository.findAll(); 
		
		
		canteenAllMenu.setItCanteenMenuList(itCanteenMenuList);
		canteenAllMenu.setMainCanteenMenuList(mainCanteenMenuList);
		canteenAllMenu.setMbaCanteenMenuList(mbaCanteenMenuList);
		canteenAllMenu.setMessMenuList(messMenuList);
		
		return canteenAllMenu;

  }
  
  
  @PostMapping("/api/auth/saveOrder")
  @PreAuthorize("hasRole('USER')")
  public void saveCustomerOrder(@RequestBody SaveOrderRequest saveOrderRequest) throws ParseException
  {
	  
	  
	  OrderList orderList=new OrderList();
	  
	  if(!orderRepository.existsByRollNo(saveOrderRequest.getRollNo()))
	  {
	  
	  orderList.setRollNo(saveOrderRequest.getRollNo());
	 
	  List<OrderMenuList> orm=new ArrayList<>();
	  
	  
	  for(OrderMenuList om : saveOrderRequest.getOrderList())
	  {
		  
		  
		  OrderMenuList nn=new OrderMenuList();
		  
		  nn.setCanteenName(om.getCanteenName());
		  nn.setItemCategory(om.getItemCategory());
		  nn.setItemName(om.getItemName());
		  nn.setItemPrice(om.getItemPrice());
		  nn.setItemQuantity(om.getItemQuantity());
		  nn.setTotalCost(om.getTotalCost());
		  nn.setCreationDateTime(new Date());
		  
		  orm.add(nn);
		 
	  }
	  
	  orderList.setSaveOrderList(orm);
	  
	  orderRepository.save(orderList);
	  
	  
	  }
	  
	  else
	  {
		  OrderList lo=orderRepository.findByRollNo(saveOrderRequest.getRollNo());
		  
		  List<OrderMenuList> result=lo.getSaveOrderList();
		  
		  
		  
		  for(OrderMenuList om : saveOrderRequest.getOrderList())
		  {
			  
			  
			  OrderMenuList nnn=new OrderMenuList();
			  
			  nnn.setCanteenName(om.getCanteenName());
			  nnn.setItemCategory(om.getItemCategory());
			  nnn.setItemName(om.getItemName());
			  nnn.setItemPrice(om.getItemPrice());
			  nnn.setItemQuantity(om.getItemQuantity());
			  nnn.setTotalCost(om.getTotalCost());
			  nnn.setCreationDateTime(new Date());
			  
			  result.add(nnn);
			 
		  }
		  
		  lo.setSaveOrderList(result);
		  
		  orderRepository.save(lo);
		  
	  }
	  


  }
  
  

  @GetMapping("/api/auth/getUserMenuHistory/{rollNo}")
  @PreAuthorize("hasRole('USER')")
  public OrderList getHistyMenu(@PathVariable String rollNo, HttpServletResponse httpServletResponse)
  {
	  
	  OrderList lo=orderRepository.findByRollNo(rollNo);
	  
	  if(lo == null)
	  {
		  httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST,"Invalid rollNo"); 
		  
	  }
	  
	  return lo;

  }
  
  
}
