package com.fooddelivery.controller;

import java.util.ArrayList;
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
import com.fooddelivery.dto.RestaurantDTO;
import com.fooddelivery.entity.MenuItems;
import com.fooddelivery.entity.Ratings;
import com.fooddelivery.entity.Restaurant;
import com.fooddelivery.exception.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping(value="/api/restaurants")
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;
    
    @Autowired
    private MenuItemsService menuItemsService;
    
    @GetMapping(produces = "application/json")
    List<RestaurantDTO> showRestaurants() throws CustomException {
        List<Restaurant> restaurantList = restaurantService.showRestaurants();
        List<RestaurantDTO> dtoList = new ArrayList<>();
        for (Restaurant restaurant : restaurantList) {
            dtoList.add(RestaurantDTO.fromRestaurant(restaurant));
        }
        return dtoList;
    }
    
    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<Object> addRestaurants(@Valid @RequestBody RestaurantDTO restaurantDTO) throws CustomException {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(restaurantDTO.getRestaurantId());
        restaurant.setRestaurantName(restaurantDTO.getRestaurantName());
        restaurant.setRestaurantAddress(restaurantDTO.getRestaurantAddress());
        restaurant.setRestaurantPhone(restaurantDTO.getRestaurantPhone());
        
        int resId = restaurantService.addRestaurants(restaurant);
        
        SuccessResponse successResponse = new SuccessResponse("Restaurant added successfully", "200");
        
        return ResponseEntity.status(HttpStatus.OK).body(
                Map.of("message", successResponse.getMessage(), "code", successResponse.getCode())
        );
    }

    
    
    @PutMapping("/{restaurantId}")
    ResponseEntity<Object> updateRestaurant(@Valid @RequestBody RestaurantDTO restaurantDTO) throws CustomException {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(restaurantDTO.getRestaurantId());
        restaurant.setRestaurantName(restaurantDTO.getRestaurantName());
        restaurant.setRestaurantAddress(restaurantDTO.getRestaurantAddress());
        restaurant.setRestaurantPhone(restaurantDTO.getRestaurantPhone());
        
        Restaurant updatedRestaurant = restaurantService.updateRestaurant(restaurant);
        RestaurantDTO updatedRestaurantDTO = RestaurantDTO.fromRestaurant(updatedRestaurant);
        
        SuccessResponse successResponse = new SuccessResponse("Restaurant updated successfully", "200");
        
        return ResponseEntity.status(HttpStatus.OK).body(
                Map.of("restaurant", updatedRestaurantDTO, "message", successResponse.getMessage(), "code", successResponse.getCode())
        );
    }

    
	@DeleteMapping("/{restaurantId}")
	ResponseEntity<String> deleteCustomer(@PathVariable("restaurantId") Integer restaurantId ) throws CustomException {
		restaurantService.deleteRestaurantByID(restaurantId);
		SuccessResponse successResponse = new SuccessResponse("Restaurants are deleted successfully", "200");
        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";
        return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
	}
	
	
	@GetMapping(path="/{restaurantId}", produces = "application/json")
	ResponseEntity<Map<String, Object>> findByRestaurantId(@PathVariable("restaurantId") Integer restaurantId) throws CustomException{
		Restaurant restaurant = restaurantService.findById(restaurantId);
        RestaurantDTO restaurantDTO = RestaurantDTO.fromRestaurant(restaurant);
		SuccessResponse successResponse = new SuccessResponse("Restaurant is fetched successfully", "200");
        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("restaurant", restaurant, "message", successResponse.getMessage(), "code", successResponse.getCode()));
	}
	
	
}


	
	


	

