package com.fooddelivery.menuitems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.menuitems.dao.MenuItemsRepository;
import com.fooddelivery.model.MenuItems;

import jakarta.transaction.Transactional;

@Service
public class MenuItemsServiceImpl implements MenuItemsService {
    @Autowired
    MenuItemsRepository menuItemsDao;
   
    @Override
    @Transactional
    public int addMenuItems(MenuItems items) {
        menuItemsDao.saveAndFlush(items);
        return items.getItemId();
    }
 
    @Override
    @Transactional
    public List<MenuItems> showMenuItems() {
        System.out.println("Service layer MenuItems called");
        return menuItemsDao.findAll();
    }
 
}