package com.fooddelivery.customer.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fooddelivery.customer.service.CustomerService;
import com.fooddelivery.exception.CustomerNotFoundException;
import com.fooddelivery.exception.DuplicateCustomerIDException;

import com.fooddelivery.exception.InvalidCustomerIDException;

import com.fooddelivery.exception.SuccessResponse;
import com.fooddelivery.model.Customer;
 
import jakarta.validation.Valid;

@ComponentScan
@RestController
@Validated
@RequestMapping(value="/api/customers")
public class CustomerController {
	@Autowired
	
    CustomerService customerService;
    @GetMapping(produces = "application/json")
        List<Customer> showCustomers() throws CustomerNotFoundException{
        	System.out.println("Customers Controller");
        	List<Customer> custList=customerService.showCustomers();
        return custList;
    }
    
    @GetMapping(path="/{customerId}", produces = "application/json")
	ResponseEntity<Map<String, Object>> findByCustomerId(@Valid @PathVariable("customerId") Integer customerId) throws CustomerNotFoundException{
		Customer res = customerService.findById(customerId);
		if(res == null) {
			throw new CustomerNotFoundException("Customer not present");
		}
		SuccessResponse successResponse = new SuccessResponse("Customer is fetched successfully", "200");
        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("Customer", res, "message", successResponse.getMessage(), "code", successResponse.getCode()));
	}
    
   
    @PostMapping(consumes = "application/json",produces = "application/json")
  	ResponseEntity<Object> addCustomers(@Valid  @RequestBody Customer customer) throws InvalidCustomerIDException, DuplicateCustomerIDException{
  	   if(customer.getCustomerId()<=0) {
  			throw new InvalidCustomerIDException("Customer ID is invalid");
  		}
  		int custId=customerService.addCustomers(customer);
  		if(custId==0) {
  			throw new DuplicateCustomerIDException("Customer with ID "+customer.getCustomerId()+" already Exists");
  		}
  		System.out.println("Customer ID in controller is "+custId);	
  		SuccessResponse successResponse = new SuccessResponse("Customers are added successfully", "200");
          String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";
          return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);	
    }

    @PutMapping("/{customerId}")
	ResponseEntity<Object> updateCustomer(@Valid @RequestBody Customer customer) throws CustomerNotFoundException{
		Customer custId=customerService.updateCustomer(customer);	
		if(customer == null) {
			throw new CustomerNotFoundException("Customer not present");
		}
		System.out.println("Customer ID in controller");		
		
		SuccessResponse successResponse = new SuccessResponse("Customers are updated successfully", "200");
        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";
		return ResponseEntity.ok(customer);
	  }
    
   
	
	@DeleteMapping("/{customerId}")
	ResponseEntity<String> deleteCustomer(@PathVariable("customerId") Integer customerId )  throws CustomerNotFoundException {
		customerService.deleteCustomerByID(customerId);
		SuccessResponse successResponse = new SuccessResponse("Customers are deleted successfully", "200");
        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";
        return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);


       }
  	  
	    
/*
	@GetMapping("/{customerId}/orders")

		ResponseEntity<Map<String, Object>> getMenuItemsByRestaurantId(@PathVariable int restaurantId) throws CustomerNotFoundException {

		    List<Customer> customer = customerService.getOrdersByCustomerId(restaurantId);

		    if (customer.isEmpty()) {

		        throw new CustomerNotFoundException("No menu items found for the restaurant ID: " + restaurantId);

		    }

		    SuccessResponse successResponse = new SuccessResponse("Restaurant is fetched successfully", "200");

	        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";

	        return ResponseEntity.status(HttpStatus.OK).body(Map.of("restaurant", customer, "message", successResponse.getMessage(), "code", successResponse.getCode()));
	 
	}

		@GetMapping("/{customerId}/reviews")

		public ResponseEntity<Map<String, Object>> getAllReviewsForCustomer(@PathVariable int customerId) throws CustomerNotFoundException {

		    List<String> reviews = customerService.getAllReviewsForCustomer(customerId);

		    if (reviews.isEmpty()) {

		        return ResponseEntity.noContent().build();

		    } else {

		        SuccessResponse successResponse = new SuccessResponse("Reviews retrieved successfully", "200");

		        return ResponseEntity.status(HttpStatus.OK).body(Map.of("reviews", reviews, "message", successResponse.getMessage(), "code", successResponse.getCode()));

		    }
}*/
}