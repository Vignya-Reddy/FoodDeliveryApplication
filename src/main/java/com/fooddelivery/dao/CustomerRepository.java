package com.fooddelivery.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fooddelivery.dto.CustomersDTO;
import com.fooddelivery.entity.Customer;
import com.fooddelivery.entity.Order;
@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Integer>{
	
}