package com.fooddelivery.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    List<Restaurant> showRestaurants() throws RestaurantNotFoundException{
        System.out.println("Restaurant Controller");
        List<Restaurant> resList= restaurantService.showRestaurants();
        return resList;
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
        SuccessResponse successResponse = new SuccessResponse("Restaurants are fetched successfully", "200");
        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";
        return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
    }
 
}
