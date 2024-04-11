package com.fooddelivery.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.entity.UserInfo;
import com.fooddelivery.jwt.AuthenticationRequest;
import com.fooddelivery.jwt.AuthenticationResponse;
import com.fooddelivery.jwt.JwtUtil;
import com.fooddelivery.jwt.MyUserDetailsService;
import com.fooddelivery.service.UserInfoService;



@RestController
@Validated
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	UserInfoService userService;
	
//	@Autowired
	//private PasswordEncoder passwordEncoder;
		

	@Autowired
	private MyUserDetailsService userDetailsService;
	
	
	@GetMapping(produces = "application/json")
	@RequestMapping(value = "/validate")
	UserInfo validateUser(){
		UserInfo user=userService.findByUserName("admin@123");
		System.out.println("user is "+user);
		return user;
	}
		
	@PostMapping(value = "/login")
	public ResponseEntity<?> createAuthenticationToken1(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		System.out.println("inside login");
		String password=authenticationRequest.getPassword();
		String username=authenticationRequest.getUsername();
	//	String encodedPassword=passwordEncoder.encode(password);
		
		//create a method which will take username and fetch the data
		
		//validate that user is present as registered user
		//User ur=validateUser(username,password)
		
		//StudentUser u=service.validateUser(authenticationRequest.getUsername(),encodedPassword);
	/*	if(u.getStudentId()<0 ){
			//throw exception, in front end if exception thrown show message invalid user in aler()
		}*/
		
		/*ur.getId()!=null ---he is registered
		 * else
		 * throw exception, in front end if exception thrown show message invalid user in aler()
		 * */
		//fllow following steps if user is registered user
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(username, password)
			);
		}
		catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Incorrect username or password", e);
		}

		System.out.println("after try");
		//String uid=String.valueOf(u.getStudentId());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		//final UserDetails userDetails = userDetailsService.loadUserByUsername(uid);

		
		// a ssuming credintial are correct will perform following step
		//String role="admin"; //get this from database based on the username
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		System.out.println("username is "+username);
		UserInfo user=userService.findByUserName(username);
		System.out.println(user.getUserId());
		
		//String userid="101";// //get this from database based on the username
		return ResponseEntity.ok(new AuthenticationResponse(jwt,user.getRole(),user.getUserId()));
	}
	
	@PostMapping(value = "/login2")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		System.out.println("inside logi");
		String password=authenticationRequest.getPassword();
		String username=authenticationRequest.getUsername();
		//create a method which will take username and fetch the data
		
		//validate that user is present as registered user
		//User ur=validateUser(username,password)
		
		//StudentUser u=service.validateUser(authenticationRequest.getUsername(),authenticationRequest.getPassword());
	/*	if(u.getStudentId()<0 ){
			//throw exception, in front end if exception thrown show message invalid user in aler()
		}*/
		
		/*ur.getId()!=null ---he is registered
		 * else
		 * throw exception, in front end if exception thrown show message invalid user in aler()
		 * */
		//fllow following steps if user is registered user
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Incorrect username or password", e);
		}

		System.out.println("after try");
		//String uid=String.valueOf(u.getStudentId());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		//final UserDetails userDetails = userDetailsService.loadUserByUsername(uid);

		
		// a ssuming credintial are correct will perform following step
		//String role="admin"; //get this from database based on the username
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		System.out.println("username is "+username);
		UserInfo user=userService.findByUserName(username);
		System.out.println(user.getUserId());
		
		//String userid="101";// //get this from database based on the username
		return ResponseEntity.ok(new AuthenticationResponse(jwt,user.getRole(),user.getUserId()));
	}

}
