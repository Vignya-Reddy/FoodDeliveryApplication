package com.fooddelivery.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @Column(name="RESTAURANT_ID")
    private int restaurantId;
    
    @NotBlank(message = "Name is required")
    @Size(max = 4, message = "Name cannot exceed 4 characters")
    @Column
    private String restaurantName;
    @Column
    private String restaurantAddress;
    
    @Digits(message="Number should contain 10 digits.", fraction = 0, integer = 10) 
    @Column
    private String restaurantPhone;
    
    @OneToMany( fetch = FetchType.EAGER, cascade= CascadeType.ALL)
    //private List<MenuItems> items = new ArrayList<>();

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
//    public List<MenuItems> getItems() {
//        return items;
//    }
//    public void setItems(List<MenuItems> items) {
//        this.items = items;
//    }
   
   
   
   
}
 