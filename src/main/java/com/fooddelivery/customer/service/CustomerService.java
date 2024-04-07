package com.fooddelivery.customer.service;
import java.util.List;

import com.fooddelivery.exception.CustomerNotFoundException;
import com.fooddelivery.exception.DuplicateCustomerIDException;
import com.fooddelivery.model.Customer;

public interface CustomerService {
	  List<Customer> showCustomers();
	  Customer updateCustomer(Customer customer) throws CustomerNotFoundException;
	  void deleteCustomerByID(int custID) throws CustomerNotFoundException;
	  int addCustomers(Customer customer) throws DuplicateCustomerIDException;
	  Customer findById(int restaurantId) throws CustomerNotFoundException;
	 // List<Customer> getOrdersByCustomerId(int customerId);
	 // List<String> getAllReviewsForCustomer(int customerId) throws CustomerNotFoundException;
	  
	  
}