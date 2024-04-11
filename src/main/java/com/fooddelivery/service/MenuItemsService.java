package com.fooddelivery.service;

import java.util.List;

import com.fooddelivery.dto.MenuItemsDTO;
import com.fooddelivery.entity.MenuItems;
import com.fooddelivery.exception.CustomException;
import com.fooddelivery.exception.DuplicateItemIDException;
import com.fooddelivery.exception.ItemNotFoundException;
import com.fooddelivery.exception.RestaurantNotFoundException;

import javax.validation.Valid;

public interface MenuItemsService {
    int addMenuItems(MenuItems items) throws CustomException;
    
    List<MenuItems> showMenuItems();
    
	void deleteMenuItemByID(int itemId) throws CustomException;

	MenuItems updateMenuItem(MenuItems items) throws CustomException;
	
	List<MenuItems> findMenuItemsByRestaurantId(int restaurantId) throws CustomException ;



	

	
	

}