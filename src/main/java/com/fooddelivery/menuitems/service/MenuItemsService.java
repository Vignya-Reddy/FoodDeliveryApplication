package com.fooddelivery.menuitems.service;

import java.util.List;

import com.fooddelivery.exception.DuplicateItemIDException;
import com.fooddelivery.exception.ItemNotFoundException;
import com.fooddelivery.model.MenuItems;

import jakarta.validation.Valid;

public interface MenuItemsService {
    int addMenuItems(MenuItems items) throws DuplicateItemIDException;
    
    List<MenuItems> showMenuItems();
    
	void deleteMenuItemByID(int itemId);

	MenuItems updateMenuItem(MenuItems items);
	

}