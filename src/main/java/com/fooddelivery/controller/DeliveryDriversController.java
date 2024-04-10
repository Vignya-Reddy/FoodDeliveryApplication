package com.fooddelivery.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.fooddelivery.entity.DeliveryDrivers;
import com.fooddelivery.entity.Order;

import com.fooddelivery.exception.CustomException;
import com.fooddelivery.service.DeliveryDriversService;
import com.fooddelivery.service.OrdersService;
import com.fooddelivery.util.SuccessResponse;

import jakarta.validation.Valid;


@RestController
@Validated
@RequestMapping(value="/api/drivers")
public class DeliveryDriversController {
	@Autowired
    DeliveryDriversService deliveryDriversService;
	
	@Autowired
    OrdersService ordersService;
	
	
	
    @GetMapping(produces = "application/json")
    ResponseEntity<Map<String, Object>> showDeliveryDrivers() throws CustomException{
        System.out.println("Delivery Driver Controller");
        List<DeliveryDrivers> driList= deliveryDriversService.showDeliveryDrivers();
        SuccessResponse successResponse = new SuccessResponse("Drivers list retrieved successfully", "200");
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("restaurants", driList, "message", successResponse.getMessage(), "code", successResponse.getCode()));
        
    }
    
    @PostMapping(consumes = "application/json",produces = "application/json")
    ResponseEntity<String> addDeliveryDrivers(@Valid @RequestBody DeliveryDrivers deliveryDrivers) throws CustomException{
//    	if(deliveryDrivers.getDeliveryDriversId()<= 0) {
//			throw new InvalidDeliveryDriversIDException("DeliveryDrivers ID is invalid");
//		}
    	
    	int delId = deliveryDriversService.addDeliveryDrivers(deliveryDrivers);
//        if(delId == 0) {
//            throw new DuplicateDeliveryDriversIDException("DeliveryDrivers is duplicate");
//        }
        System.out.println("DeliveryDrivers Id in controller is "+delId);
        SuccessResponse successResponse = new SuccessResponse("Restaurants are fetched successfully", "200");
        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";
        return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
    }
     
    @GetMapping("/{driverId}")
	ResponseEntity<Map<String, Object>> findByDriverId(@PathVariable("driverId") Integer driverId) throws CustomException{
    	DeliveryDrivers driver = deliveryDriversService.findById(driverId);
		if(driver == null) {
			throw new CustomException("Customer not present");
		}
		SuccessResponse successResponse = new SuccessResponse("Driver is fetched successfully", "200");
        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("driver", driver, "message", successResponse.getMessage(), "code", successResponse.getCode()));
	}
    
    
    @PutMapping("/api/orders/{orderId}/assignDriver/{driverId}")
    public ResponseEntity<String> assignDriverToOrder(@PathVariable int orderId, @PathVariable int driverId) throws CustomException {
        Order order = ordersService.getOrderById(orderId);
        if (order == null) {
            throw new CustomException("Order not found");
        }

        DeliveryDrivers driver = deliveryDriversService.findById(driverId);
        if (driver == null) {
            throw new CustomException("Driver not found");
        }

        order.setDeliveryDrivers(driver);
        ordersService.updateOrder(order);

        SuccessResponse successResponse = new SuccessResponse("Driver assigned to the order successfully", "200");
        return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}");
    }
    
    @GetMapping("/{driverId}/orders")
    ResponseEntity<Map<String, Object>> getOrdersByDriverId(@PathVariable int driverId) throws  CustomException {
        List<Order> orders = deliveryDriversService.getOrdersByDriverId(driverId);
        
        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            SuccessResponse successResponse = new SuccessResponse("Orders retrieved successfully", "200");
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("orders", orders, "message", successResponse.getMessage(), "code", successResponse.getCode()));
        }
    }
 
}
