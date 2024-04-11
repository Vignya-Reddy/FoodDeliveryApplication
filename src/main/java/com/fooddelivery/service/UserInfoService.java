package com.fooddelivery.service;

import org.springframework.stereotype.Service;

import com.fooddelivery.entity.UserInfo;

@Service
public interface UserInfoService {
	UserInfo findByUserName(String username);
	}

