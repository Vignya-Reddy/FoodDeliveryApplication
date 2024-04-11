package com.fooddelivery.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.dto.RatingsDTO;
import com.fooddelivery.entity.Ratings;
import com.fooddelivery.exception.CustomException;
import com.fooddelivery.service.CustomerService;
import com.fooddelivery.service.RatingsService;
import com.fooddelivery.util.SuccessResponse;

@RestController
@Validated
@RequestMapping(value="api/ratings")
public class RatingsController {
	
	@Autowired
    RatingsService ratingsService;
	
		
	@GetMapping("/{restaurantId}/reviews")
    public ResponseEntity<Map<String, Object>> getReviewsForRestaurant(@PathVariable int restaurantId) throws CustomException {
        List<String> reviews = ratingsService.getReviewsForRestaurant(restaurantId);
        if (reviews.isEmpty()) {
            throw new CustomException("No reviews found for restaurant with ID " + restaurantId);
        }
        SuccessResponse successResponse = new SuccessResponse("Reviews retrieved successfully", "200");
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("reviews", reviews, "message", successResponse.getMessage(), "code", successResponse.getCode()));
    }
	
}
