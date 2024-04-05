package com.fooddelivery.menuitems.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fooddelivery.model.MenuItems;

@Repository
public interface MenuItemsRepository extends JpaRepository<MenuItems, Integer>{

//	List<MenuItems> findByRestaurantName(String restaurantName);
 
}
