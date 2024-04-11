package com.fooddelivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.dao.UserRepository;
import com.fooddelivery.entity.UserInfo;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	UserRepository userDao;
	
    @Override
    public UserInfo findByUserName(String username) {
		return userDao.findByUserName(username);
	}
}
