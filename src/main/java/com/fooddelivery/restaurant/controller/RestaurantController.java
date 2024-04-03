package com.fooddelivery.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.fooddelivery.model.Restaurant;
import com.fooddelivery.restaurant.service.RestaurantService;

import jakarta.validation.Valid;

@RestController
@Validated
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
    ResponseEntity<Restaurant> addRestaurants(@Valid @RequestBody Restaurant restaurant) throws DuplicateRestaurantIDException, InvalidRestaurantIDException{
    	if(restaurant.getRestaurantId()<= 0) {
			throw new InvalidRestaurantIDException("Restaurant ID is invalid");
		}
    	
    	int resId = restaurantService.addRestaurants(restaurant);
        if(resId == 0) {
            throw new DuplicateRestaurantIDException("Restaurant is duplicate");
        }
        System.out.println("Restaurant Id in controller is "+resId);
        return ResponseEntity.ok(restaurant);
    }
 
}
