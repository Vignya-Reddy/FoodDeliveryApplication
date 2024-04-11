
package com.fooddelivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.dao.MenuItemsRepository;
import com.fooddelivery.dto.MenuItemsDTO;
import com.fooddelivery.entity.MenuItems;
import com.fooddelivery.exception.CustomException;
import com.fooddelivery.exception.DuplicateItemIDException;
import com.fooddelivery.exception.ItemNotFoundException;
import com.fooddelivery.exception.RestaurantNotFoundException;

import javax.transaction.Transactional;

@Service
public class MenuItemsServiceImpl implements MenuItemsService {
    @Autowired
    MenuItemsRepository menuItemsDao;
   
    @Override
    @Transactional
    public int addMenuItems(MenuItems items) throws CustomException {
    	Optional<MenuItems> menu=menuItemsDao.findById(items.getItemId());
		if(menu.isPresent()) {			
				throw new CustomException("Item with Id"+items.getItemId()+" already Exists");
			}
		menuItemsDao.saveAndFlush(items);
		return items.getItemId();
    }
 
    @Override
    @Transactional
    public List<MenuItems> showMenuItems() {
        System.out.println("Service layer MenuItems called");
        return menuItemsDao.findAll();
    }
    
    @Transactional
    @Override
    public MenuItems updateMenuItem(MenuItems updatedItem) throws CustomException {
        Optional<MenuItems> existingItemOptional = menuItemsDao.findById(updatedItem.getItemId());
        if (!existingItemOptional.isPresent()) {
            throw new CustomException("Item with ID " + updatedItem.getItemId() + " not found");
        }
        
        MenuItems existingItem = existingItemOptional.get();
        existingItem.setItemName(updatedItem.getItemName());
        existingItem.setItemDescription(updatedItem.getItemDescription());
        existingItem.setItemPrice(updatedItem.getItemPrice());

        // Save the updated item
        return menuItemsDao.save(existingItem);
    }



    @Override
    @Transactional
    public void deleteMenuItemByID(int itemId) throws CustomException {
        Optional<MenuItems> menuItem = menuItemsDao.findById(itemId);
        if (menuItem.isEmpty()) {
            throw new CustomException("Item not found");
        } else {
            menuItemsDao.deleteById(itemId);
        }
    }
    
    @Override
    public List<MenuItems> findMenuItemsByRestaurantId(int restaurantId) throws CustomException {
        List<MenuItems> menuItems = menuItemsDao.findByRestaurantId(restaurantId);
        if (menuItems.isEmpty()) {
            throw new CustomException("No menu items found for restaurant with ID: " + restaurantId);
        }
        return menuItems;
    }

    

   
    
    

}
