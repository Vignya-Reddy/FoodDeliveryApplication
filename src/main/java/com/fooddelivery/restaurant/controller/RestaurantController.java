package com.fooddelivery.restaurant.controller;

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


import com.fooddelivery.exception.DuplicateRestaurantIDException;

import com.fooddelivery.exception.InvalidRestaurantIDException;
import com.fooddelivery.exception.RestaurantNotFoundException;
import com.fooddelivery.exception.SuccessResponse;
import com.fooddelivery.model.Restaurant;
import com.fooddelivery.restaurant.service.RestaurantService;
import com.fooddelivery.exception.*;

import jakarta.validation.Valid;

@ComponentScan
@RestController

@RequestMapping(value="/api/restaurants")
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;
    @GetMapping(produces = "application/json")
    ResponseEntity<Map<String, Object>> showRestaurants() throws RestaurantNotFoundException{
        System.out.println("Restaurant Controller");
        List<Restaurant> resList= restaurantService.showRestaurants();
        SuccessResponse successResponse = new SuccessResponse("Restaurant list retrieved successfully", "200");
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("restaurants", resList, "message", successResponse.getMessage(), "code", successResponse.getCode()));
        
    }
   
    @PostMapping(consumes = "application/json",produces = "application/json")
    ResponseEntity<String> addRestaurants(@Valid @RequestBody Restaurant restaurant) throws DuplicateRestaurantIDException, InvalidRestaurantIDException{
    	if(restaurant.getRestaurantId()<= 0) {
			throw new InvalidRestaurantIDException("Restaurant ID is invalid");
		}
    	
    	int resId = restaurantService.addRestaurants(restaurant);
        if(resId == 0) {
            throw new DuplicateRestaurantIDException("Restaurant is duplicate");
        }
        System.out.println("Restaurant Id in controller is "+resId);
        SuccessResponse successResponse = new SuccessResponse("Restaurants are added successfully", "200");
        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";
        return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
    }
    
    @PutMapping("/{restaurantId}")
	ResponseEntity<String> updateRestaurant(@Valid @RequestBody Restaurant restaurant){
		Restaurant res = restaurantService.updateRestaurant(restaurant);
		System.out.println("Restaurant ID in controller");		
		SuccessResponse successResponse = new SuccessResponse("Restaurants are updated successfully", "200");
        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";
        return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
	}
	@DeleteMapping("/{restaurantId}")
	ResponseEntity<String> deleteCustomer(@PathVariable("restaurantId") Integer restaurantId ) {
		restaurantService.deleteRestaurantByID(restaurantId);
		SuccessResponse successResponse = new SuccessResponse("Restaurants are deleted successfully", "200");
        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";
        return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
	}
	
	@GetMapping(path="/{restaurantId}", produces = "application/json")
	ResponseEntity<Map<String, Object>> findByRestaurantId(@PathVariable("restaurantId") Integer restaurantId) throws RestaurantNotFoundException{
		Restaurant res = restaurantService.findById(restaurantId);
		if(res == null) {
			throw new RestaurantNotFoundException("Customer not present");
		}
		SuccessResponse successResponse = new SuccessResponse("Restaurant is fetched successfully", "200");
        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("restaurant", res, "message", successResponse.getMessage(), "code", successResponse.getCode()));
	}
 
}
