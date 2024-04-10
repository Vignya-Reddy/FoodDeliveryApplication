package com.fooddelivery.dto;

import com.fooddelivery.entity.MenuItems;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MenuItemsDTO {
    private int itemId;
    private String itemName;
    private String itemDescription;
    private double itemPrice;
    private RestaurantDTO restaurant;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public RestaurantDTO getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDTO restaurant) {
        this.restaurant = restaurant;
    }

    public static MenuItemsDTO fromMenuItems(MenuItems menuItem) {
        MenuItemsDTO dto = new MenuItemsDTO();
        dto.setItemId(menuItem.getItemId());
        dto.setItemName(menuItem.getItemName());
        dto.setItemDescription(menuItem.getItemDescription());
        dto.setItemPrice(menuItem.getItemPrice());
        dto.setRestaurant(RestaurantDTO.fromRestaurant(menuItem.getRestaurant()));
        return dto;
    }
}
