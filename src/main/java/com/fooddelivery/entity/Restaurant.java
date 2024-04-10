package com.fooddelivery.entity;

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
    @Size(max = 20, message = "Name cannot exceed 20 characters")
    @Column(name = "RESTAURANT_NAME")
    private String restaurantName;
    @Column(name = "RESTAURANT_ADDRESS")
    private String restaurantAddress;
    
    //@Digits(message="Number should contain 10 digits.", fraction = 0, integer = 10) 
    @Column(name = "RESTAURANT_PHONE")
    private String restaurantPhone;
    
    
    public Restaurant(int restaurantId,@NotBlank(message = "Name is required") @Size(max = 20, message = "Name cannot exceed 20 characters") String restaurantName,
			String restaurantAddress, String restaurantPhone) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.restaurantAddress = restaurantAddress;
		this.restaurantPhone = restaurantPhone;
	}
    
    public Restaurant() {
    	
    }
	@OneToMany( fetch = FetchType.EAGER, cascade= CascadeType.ALL)
	
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

   
   
   
   
}
 