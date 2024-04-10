package com.fooddelivery.service;

import java.util.List;

import com.fooddelivery.dto.MenuItemsDTO;
import com.fooddelivery.dto.RestaurantDTO;
import com.fooddelivery.entity.DeliveryAddress;
import com.fooddelivery.entity.MenuItems;
import com.fooddelivery.entity.Ratings;
import com.fooddelivery.entity.Restaurant;
import com.fooddelivery.exception.CustomException;
import com.fooddelivery.exception.DuplicateRestaurantIDException;
import com.fooddelivery.exception.RestaurantNotFoundException;

public interface RestaurantService {
    int addRestaurants(Restaurant restaurant) throws CustomException;
    
    List<Restaurant> showRestaurants();
    
    Restaurant updateRestaurant(Restaurant restaurant);
	
	void deleteRestaurantByID(int restaurantId);

	Restaurant findById(int restaurantId) throws CustomException;
	
    List<MenuItems> getMenuItemsByRestaurantId(int restaurantId);

    //List<String> getAllReviewsForRestaurant(int restaurantId) throws CustomException;

	
	

}




