package com.fooddelivery.dto;

import com.fooddelivery.entity.Ratings;

public class RatingsDTO {
    private int ratingId;
    private int rating;
    private String review;
    private RestaurantDTO restaurant;
    private OrdersDTO order;

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public RestaurantDTO getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDTO restaurant) {
        this.restaurant = restaurant;
    }

    public OrdersDTO getOrder() {
        return order;
    }

    public void setOrder(OrdersDTO order) {
        this.order = order;
    }

    public static RatingsDTO fromRatings(Ratings rating) {
        RatingsDTO dto = new RatingsDTO();
        dto.setRatingId(rating.getRating_id());
        dto.setRating(rating.getRating());
        dto.setReview(rating.getReview());
        dto.setRestaurant(RestaurantDTO.fromRestaurant(rating.getRestaurant()));
        dto.setOrder(OrdersDTO.fromOrders(rating.getOrder()));
        return dto;
    }

	 
}
