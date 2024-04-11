package com.fooddelivery.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
 