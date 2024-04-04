package com.fooddelivery.restaurant.service;

import java.util.List;

import com.fooddelivery.exception.DuplicateRestaurantIDException;
import com.fooddelivery.exception.RestaurantNotFoundException;
import com.fooddelivery.model.Restaurant;

public interface RestaurantService {
    int addRestaurants(Restaurant restaurant) throws DuplicateRestaurantIDException;
    List<Restaurant> showRestaurants();
    Restaurant updateRestaurant(Restaurant restaurant);
	
	void deleteRestaurantByID(int restaurantId);

	Restaurant findById(int restaurantId) throws RestaurantNotFoundException;
 
}