package com.fooddelivery.menuitems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.exception.DuplicateItemIDException;
import com.fooddelivery.exception.ItemNotFoundException;
import com.fooddelivery.menuitems.dao.MenuItemsRepository;
import com.fooddelivery.model.MenuItems;

import jakarta.transaction.Transactional;

@Service
public class MenuItemsServiceImpl implements MenuItemsService {
    @Autowired
    MenuItemsRepository menuItemsDao;
   
    @Override
    @Transactional
    public int addMenuItems(MenuItems items) throws DuplicateItemIDException {
    	Optional<MenuItems> menu=menuItemsDao.findById(items.getItemId());
		if(menu.isPresent()) {			
				throw new DuplicateItemIDException("Item with Id"+items.getItemId()+" already Exists");
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
	public MenuItems updateMenuItem(MenuItems items) {
		
		Optional<MenuItems> previouItem= menuItemsDao.findById(items.getItemId());
		MenuItems itemupdated=previouItem.get();
		//Do not change the customer id using setter method
		itemupdated.setItemName(items.getItemName()); //automatically update data in table
	
		return itemupdated;
	}


	@Override
	@Transactional
	public void deleteMenuItemByID(int itemId) {
		menuItemsDao.deleteById(itemId);
		
	}
    
    
 
}