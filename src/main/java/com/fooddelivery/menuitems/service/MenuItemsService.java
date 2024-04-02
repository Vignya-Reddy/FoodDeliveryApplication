package com.fooddelivery.menuitems.service;

import java.util.List;

import com.fooddelivery.model.MenuItems;

public interface MenuItemsService {
    int addMenuItems(MenuItems items);
    List<MenuItems> showMenuItems();
}