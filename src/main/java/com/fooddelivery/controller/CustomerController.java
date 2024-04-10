package com.fooddelivery.controller;
import java.util.ArrayList;
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

import com.fooddelivery.dto.CustomersDTO;
import com.fooddelivery.entity.Customer;
import com.fooddelivery.entity.Order;
import com.fooddelivery.entity.Ratings;
import com.fooddelivery.exception.CustomException;
import com.fooddelivery.exception.CustomerNotFoundException;
import com.fooddelivery.exception.DuplicateCustomerIDException;

import com.fooddelivery.exception.InvalidCustomerIDException;
import com.fooddelivery.exception.OrdersNotFoundException;
import com.fooddelivery.exception.ReviewsNotFoundException;
import com.fooddelivery.service.CustomerService;
import com.fooddelivery.util.SuccessResponse;

import jakarta.validation.Valid;


@RestController
@Validated
@RequestMapping(value="/api/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    
    @GetMapping(produces = "application/json")
    List<CustomersDTO> showCustomers() throws CustomException{
        List<Customer> custList = customerService.showCustomers();
        List<CustomersDTO> dtoList = new ArrayList<>();
        for (Customer customer : custList) {
            dtoList.add(CustomersDTO.fromCustomer(customer));
        }
        return dtoList;
    }
    
    @GetMapping(path="/{customerId}", produces = "application/json")
    ResponseEntity<Map<String, Object>> findByCustomerId(@Valid @PathVariable("customerId") Integer customerId) throws CustomException{
        Customer customer = customerService.findByCustomerId(customerId);
//        if (customer == null) {
//            throw new CustomerNotFoundException("Customer not found");
//        }
        CustomersDTO customerDTO = CustomersDTO.fromCustomer(customer);
        
        SuccessResponse successResponse = new SuccessResponse("Customer is fetched successfully", "200");
        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";
        
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("Customer", customerDTO, "message", successResponse.getMessage(), "code", successResponse.getCode()));
    }
       
    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<Object> addCustomers(@Valid @RequestBody CustomersDTO customerDTO) throws CustomException{
        Customer customer = new Customer();
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setCustomerEmail(customerDTO.getCustomerEmail());
        customer.setCustomerPhone(customerDTO.getCustomerPhone());
        
        int custId = customerService.addCustomers(customer);
        
        
        SuccessResponse successResponse = new SuccessResponse("Customers are added successfully", "200");
        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";
        
        return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);	
    }

    @PutMapping("/{customerId}")
    ResponseEntity<Object> updateCustomer(@Valid @RequestBody CustomersDTO customerDTO) throws CustomException{
        Customer customer = new Customer();
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setCustomerEmail(customerDTO.getCustomerEmail());
        customer.setCustomerPhone(customerDTO.getCustomerPhone());
        
        Customer updatedCustomer = customerService.updateCustomer(customer);
        CustomersDTO updatedCustomerDTO = CustomersDTO.fromCustomer(updatedCustomer);
		
        SuccessResponse successResponse = new SuccessResponse("Customers are updated successfully", "200");
        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";
        
        return ResponseEntity.ok(updatedCustomerDTO);
    }
    	
    @DeleteMapping("/{customerId}")
    ResponseEntity<String> deleteCustomer(@PathVariable("customerId") Integer customerId ) throws CustomException {
        customerService.deleteCustomerByID(customerId);
        
        SuccessResponse successResponse = new SuccessResponse("Customers are deleted successfully", "200");
        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";
        
        return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
    }
}
