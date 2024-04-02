package com.fooddelivery.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.model.Restaurant;
import com.fooddelivery.restaurant.service.RestaurantService;

@RestController
@RequestMapping(value="/api/restaurants")
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;
    @GetMapping(produces = "application/json")
    List<Restaurant> showRestaurants(){
        System.out.println("Restaurants Controller");
        List<Restaurant> resList= restaurantService.showRestaurants();
        return resList;
    }
   
    @PostMapping(consumes = "application/json",produces = "application/json")
    ResponseEntity<Restaurant> addRestaurants(Restaurant restaurant){
        int resId = restaurantService.addRestaurants(restaurant);
        return ResponseEntity.ok(restaurant);
    }
 
}
