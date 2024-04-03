package com.fooddelivery.menuitems.service;

import java.util.List;

import com.fooddelivery.exception.DuplicateItemIDException;
import com.fooddelivery.model.MenuItems;

public interface MenuItemsService {
    int addMenuItems(MenuItems items) throws DuplicateItemIDException;
    List<MenuItems> showMenuItems();
}