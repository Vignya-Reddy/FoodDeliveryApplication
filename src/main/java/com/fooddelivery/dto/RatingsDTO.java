package com.fooddelivery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RatingsDTO {
    private int ratingId;

    @NotNull(message = "Rating is required")
    private int rating;

    private String review;

    private int restaurantId;

    private int orderId;

    // Getters and setters
    // You may also add constructors and other methods as needed

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

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "RatingsDTO [ratingId=" + ratingId + ", rating=" + rating + ", review=" + review + ", restaurantId="
                + restaurantId + ", orderId=" + orderId + "]";
    }
}
