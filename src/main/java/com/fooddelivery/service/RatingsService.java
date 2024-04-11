package com.fooddelivery.service;

import java.util.List;

import com.fooddelivery.entity.Ratings;
import com.fooddelivery.exception.CustomException;

public interface RatingsService {
	List<String> getReviewsForRestaurant(int restaurantId) throws CustomException;
	
}
