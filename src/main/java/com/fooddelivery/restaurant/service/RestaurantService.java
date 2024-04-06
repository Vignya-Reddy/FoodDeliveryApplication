package com.fooddelivery.restaurant.service;

import java.util.List;

import com.fooddelivery.exception.DuplicateRestaurantIDException;
import com.fooddelivery.exception.RestaurantNotFoundException;
import com.fooddelivery.model.DeliveryAddress;
import com.fooddelivery.model.MenuItems;
import com.fooddelivery.model.Ratings;
import com.fooddelivery.model.Restaurant;

public interface RestaurantService {
    int addRestaurants(Restaurant restaurant) throws DuplicateRestaurantIDException;
    
    List<Restaurant> showRestaurants();
    
    Restaurant updateRestaurant(Restaurant restaurant);
	
	void deleteRestaurantByID(int restaurantId);

	Restaurant findById(int restaurantId) throws RestaurantNotFoundException;
	
    List<MenuItems> getMenuItemsByRestaurantId(int restaurantId);

    List<String> getAllReviewsForRestaurant(int restaurantId) throws RestaurantNotFoundException;

	
	

}




