package com.fooddelivery.jwt;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fooddelivery.entity.UserInfo;
import com.fooddelivery.service.UserInfoService;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserInfoService srv;
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	//code to finde user with that username and password
    	//if it is not null
    	UserInfo u=srv.findByUserName(username);
    	if(u!=null) {//throw exception
    		}
    	
    	return new User(u.getUserName(), u.getPassword(), new ArrayList<>());
        //return new User("priya", "priya@123", new ArrayList<>());
    
    }  	
}