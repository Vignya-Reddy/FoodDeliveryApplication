package com.fooddelivery.restaurant.service;

import java.util.List;

import com.fooddelivery.model.Restaurant;

public interface RestaurantService {
    int addRestaurants(Restaurant restaurant);
    List<Restaurant> showRestaurants();
 
}