package com.fooddelivery.orders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.fooddelivery.exception.DuplicateOrderIDException;
import com.fooddelivery.exception.InvalidOrderIDException;
import com.fooddelivery.exception.OrdersNotFoundException;
import com.fooddelivery.exception.SuccessResponse;
import com.fooddelivery.model.Order;
import com.fooddelivery.orders.service.OrdersService;
import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping(value = "/api/orders")
public class OrdersController {
	 @Autowired
	    OrdersService orderService;
	    @GetMapping(produces = "application/json")
	    List<Order> showOrders()  throws OrdersNotFoundException{
	        System.out.println("Orders Controller");
	        List<Order> ordersList=orderService.showOrders();
	        return ordersList;
	    }
	   
	    @PostMapping(consumes = "application/json",produces = "application/json")
		ResponseEntity<Object> addOrders(@Valid  @RequestBody Order orders) throws DuplicateOrderIDException, InvalidOrderIDException{
		   if(orders.getOrderId()<=0) {
				throw new InvalidOrderIDException("Order ID is invalid");
			}
			int orderId=orderService.addOrders(orders);
			if(orderId==0) {
				throw new DuplicateOrderIDException("Order with ID "+orders.getOrderId()+" already Exists");
			}
			System.out.println("Order ID in controller is "+orderId);	
			SuccessResponse successResponse = new SuccessResponse("Orders are fetched successfully", "200");
	        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";
	        return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
		}
	    
	    @PutMapping("/{orderId}/status")
		ResponseEntity<String> updateOrder(@Valid @RequestBody Order ord) throws OrdersNotFoundException{
			Order orderId = orderService.updateOrder(ord);
			System.out.println("Order ID in controller");		
			SuccessResponse successResponse = new SuccessResponse("Orders are updated successfully", "200");
	        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";
	        return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
		}
	    
	  
	    
	    @DeleteMapping("/{orderId}")
		ResponseEntity<Object> deleteOrder(@PathVariable("orderId") Integer orderId ) {
			orderService.deleteOrderByID(orderId);
			SuccessResponse successResponse = new SuccessResponse("Items are deleted successfully", "200");
	        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";
	        return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
		}
	    

}
