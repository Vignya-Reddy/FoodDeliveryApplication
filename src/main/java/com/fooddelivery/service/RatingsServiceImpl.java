package com.fooddelivery.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.dao.RatingsRepository;
import com.fooddelivery.entity.Ratings;
import com.fooddelivery.entity.Restaurant;
import com.fooddelivery.exception.CustomException;

@Service
public class RatingsServiceImpl  implements RatingsService{
	@Autowired
    RatingsRepository ratingsDao;
	
	@Autowired
    RestaurantService restaurantService;
	
	
	
	@Override
    @Transactional
    public List<String> getReviewsForRestaurant(int restaurantId) throws CustomException {
        // Check if the restaurant exists
        Restaurant restaurant = restaurantService.findById(restaurantId);
        if (restaurant == null) {
            throw new CustomException("Restaurant with ID " + restaurantId + " not found");
        }
        
        // Fetch reviews only if the restaurant exists
        return ratingsDao.findByRestaurantId(restaurantId)
                .stream()
                .map(Ratings::getReview)
                .collect(Collectors.toList());
    }
}
