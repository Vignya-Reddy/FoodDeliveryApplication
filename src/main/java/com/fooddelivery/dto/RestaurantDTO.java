package com.fooddelivery.dto;

import com.fooddelivery.entity.Restaurant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RestaurantDTO {
    private int restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantPhone;

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantPhone() {
        return restaurantPhone;
    }

    public void setRestaurantPhone(String restaurantPhone) {
        this.restaurantPhone = restaurantPhone;
    }

    public static RestaurantDTO fromRestaurant(Restaurant restaurant) {
        RestaurantDTO dto = new RestaurantDTO();
        dto.setRestaurantId(restaurant.getRestaurantId());
        dto.setRestaurantName(restaurant.getRestaurantName());
        dto.setRestaurantAddress(restaurant.getRestaurantAddress());
        dto.setRestaurantPhone(restaurant.getRestaurantPhone());
        return dto;
    }
}

