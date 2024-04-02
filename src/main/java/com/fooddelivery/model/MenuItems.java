package com.fooddelivery.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "menuitems")
public class MenuItems implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@SequenceGenerator(name= "menuitems_seq",sequenceName = "menuitems_seq",allocationSize=1)
    @Column(name="ITEM_ID")
    private int itemId;
    @Column(name="ITEM_NAME")
    private String itemName;
    @Column(name="ITEM_DESCRIPTION")
    private String itemDescription;
    @Column(name="ITEM_PRICE")
    private double itemPrice;
   
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
 
 
}
 
