package com.fooddelivery.service;
import java.util.List;
import java.util.Optional;

import com.fooddelivery.dto.CustomersDTO;
import com.fooddelivery.entity.Customer;
import com.fooddelivery.entity.Order;
import com.fooddelivery.entity.Ratings;
import com.fooddelivery.exception.CustomException;
import com.fooddelivery.exception.CustomerNotFoundException;
import com.fooddelivery.exception.DuplicateCustomerIDException;
import com.fooddelivery.exception.OrdersNotFoundException;
import com.fooddelivery.exception.ReviewsNotFoundException;

public interface CustomerService {
	  List<Customer> showCustomers();
	  Customer updateCustomer(Customer customer) throws CustomException;
	  void deleteCustomerByID(int custID) throws CustomException;
	  int addCustomers(Customer customer) throws CustomException;
	  Customer findByCustomerId(int restaurantId) throws CustomException;
	//List<Order> getOrdersByCustomerId(int customerId) throws CustomException;
	
	
}