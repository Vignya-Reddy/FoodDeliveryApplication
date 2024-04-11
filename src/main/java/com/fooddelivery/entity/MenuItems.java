package com.fooddelivery.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "menuitems")
public class MenuItems implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@SequenceGenerator(name= "menuitems_seq",sequenceName = "menuitems_seq",allocationSize=1)
    @Column(name="ITEM_ID")
    private int itemId;
    
    @NotBlank(message = "Name is required")
    //@Size(max = 100, message = "Name cannot exceed 100 characters")
    @Column(name="ITEM_NAME")
    private String itemName;
    @Column(name="ITEM_DESCRIPTION")
    private String itemDescription;
    @Column(name="ITEM_PRICE")
    private double itemPrice;
    

	public MenuItems(int itemId, @NotBlank(message = "Name is required") String itemName, String itemDescription,
			double itemPrice) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemPrice = itemPrice;
	}
	
	public MenuItems() {
		
	}

	@ManyToOne
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;
   
    
	public Restaurant getRestaurant() {
        return restaurant;
    }
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
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
   
    @Override
    public String toString() {
        return "MenuItems [itemId=" + itemId + ", itemName=" + itemName + ", description=" + itemDescription + ", price=" + itemPrice+  "]";
    }

	public int getRestaurantId() {
		// TODO Auto-generated method stub
		return restaurant.getRestaurantId();
	}

	
 
 
}
 
