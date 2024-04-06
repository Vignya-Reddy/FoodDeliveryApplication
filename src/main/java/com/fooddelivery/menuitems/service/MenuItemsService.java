package com.fooddelivery.menuitems.service;

import java.util.List;

import com.fooddelivery.exception.DuplicateItemIDException;
import com.fooddelivery.exception.ItemNotFoundException;
import com.fooddelivery.exception.RestaurantNotFoundException;
import com.fooddelivery.model.MenuItems;

import jakarta.validation.Valid;

public interface MenuItemsService {
    int addMenuItems(MenuItems items) throws DuplicateItemIDException;
    
    List<MenuItems> showMenuItems();
    
	void deleteMenuItemByID(int itemId) throws ItemNotFoundException;

	MenuItems updateMenuItem(MenuItems items) throws ItemNotFoundException;

	List<MenuItems> getMenuItemsByRestaurantId(int restaurantId) throws RestaurantNotFoundException;
	

}