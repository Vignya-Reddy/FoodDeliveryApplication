package com.fooddelivery.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fooddelivery.entity.UserInfo;


@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer>{
	public UserInfo findByUserName(String userName);
}

