package com.fooddelivery.controller;

import java.util.List;
import java.util.Map;

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

import com.fooddelivery.service.MenuItemsService;
import com.fooddelivery.service.RestaurantService;
import com.fooddelivery.util.SuccessResponse;
import com.fooddelivery.dto.MenuItemsDTO;
import com.fooddelivery.dto.RestaurantDTO;
import com.fooddelivery.entity.DeliveryAddress;
import com.fooddelivery.entity.MenuItems;
import com.fooddelivery.entity.Ratings;
import com.fooddelivery.entity.Restaurant;
import com.fooddelivery.exception.*;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping(value="/api/restaurants")
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;
    
    @Autowired
    private MenuItemsService menuItemsService;
    
    @GetMapping(produces = "application/json")
    ResponseEntity<Map<String, Object>> showRestaurants() throws CustomException{
        System.out.println("Restaurant Controller");
        List<Restaurant> resList= restaurantService.showRestaurants();
        SuccessResponse successResponse = new SuccessResponse("Restaurant list retrieved successfully", "200");
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("restaurants", resList, "message", successResponse.getMessage(), "code", successResponse.getCode()));
        
    }
    
    @PostMapping(consumes = "application/json",produces = "application/json")
    ResponseEntity<String> addRestaurants(@Valid @RequestBody Restaurant restaurant) throws CustomException{
//    	if(restaurant.getRestaurantId()<= 0) {
//			throw new InvalidRestaurantIDException("Restaurant ID is invalid");
//		}
    	
    	int resId = restaurantService.addRestaurants(restaurant);
//        if(resId == 0) {
//            throw new DuplicateRestaurantIDException("Restaurant is duplicate");
//        }
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
	ResponseEntity<Map<String, Object>> findByRestaurantId(@PathVariable("restaurantId") Integer restaurantId) throws CustomException{
		Restaurant res = restaurantService.findById(restaurantId);
		if(res == null) {
			throw new CustomException("Customer not present");
		}
		SuccessResponse successResponse = new SuccessResponse("Restaurant is fetched successfully", "200");
        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("restaurant", res, "message", successResponse.getMessage(), "code", successResponse.getCode()));
	}
	
	@GetMapping("/{restaurantId}/menu")
	ResponseEntity<Map<String, Object>> getMenuItemsByRestaurantId(@PathVariable int restaurantId) throws CustomException {
	    List<MenuItems> menuItems = menuItemsService.getMenuItemsByRestaurantId(restaurantId);
//	    if (menuItems.isEmpty()) {
//	        throw new RestaurantNotFoundException("No menu items found for the restaurant ID: " + restaurantId);
//	    }
	    SuccessResponse successResponse = new SuccessResponse("Restaurant is fetched successfully", "200");
        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("restaurant", menuItems, "message", successResponse.getMessage(), "code", successResponse.getCode()));

}
	
//	@GetMapping("/{restaurantId}/reviews")
//	public ResponseEntity<Map<String, Object>> getAllReviewsForRestaurant(@PathVariable int restaurantId) throws CustomException {
//	    List<String> reviews = restaurantService.getAllReviewsForRestaurant(restaurantId);
//	    if (reviews.isEmpty()) {
//	        return ResponseEntity.noContent().build();
//	    } else {
//	        SuccessResponse successResponse = new SuccessResponse("Reviews retrieved successfully", "200");
//	        return ResponseEntity.status(HttpStatus.OK).body(Map.of("reviews", reviews, "message", successResponse.getMessage(), "code", successResponse.getCode()));
//	    }
//	}

}
