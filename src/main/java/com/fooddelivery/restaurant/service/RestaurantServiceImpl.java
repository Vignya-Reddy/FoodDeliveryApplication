package com.fooddelivery.restaurant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.exception.DuplicateItemIDException;
import com.fooddelivery.exception.DuplicateRestaurantIDException;
import com.fooddelivery.exception.RestaurantNotFoundException;
import com.fooddelivery.menuitems.dao.MenuItemsRepository;
import com.fooddelivery.model.MenuItems;
import com.fooddelivery.model.Restaurant;
import com.fooddelivery.restaurant.dao.RestaurantRepository;

import jakarta.transaction.Transactional;

@Service
public class RestaurantServiceImpl implements RestaurantService {
 
    @Autowired
    RestaurantRepository restaurantDao;
    
    @Autowired
    private MenuItemsRepository menuItemsRepository;
   
   
    @Override
    @Transactional
    public List<Restaurant> showRestaurants() {
        System.out.println("Service layer Restaurant called");
        return restaurantDao.findAll();
    }
 

    @Override
    @Transactional
    public int addRestaurants(Restaurant restaurant) throws DuplicateRestaurantIDException {
    	Optional<Restaurant> res=restaurantDao.findById(restaurant.getRestaurantId());
		if(res.isPresent()) {			
				throw new DuplicateRestaurantIDException("Restaurant with Id"+restaurant.getRestaurantId()+" already Exists");
			}
		restaurantDao.saveAndFlush(restaurant);
		return restaurant.getRestaurantId();
    }
    
    @Transactional
	@Override
	public Restaurant updateRestaurant(Restaurant restaurant) {
		
		Optional<Restaurant> previousRestaurant= restaurantDao.findById(restaurant.getRestaurantId());
		Restaurant resupdated=previousRestaurant.get();
		
		resupdated.setRestaurantName(restaurant.getRestaurantName()); 
	
		return resupdated;
	}


	@Override
	@Transactional
	public void deleteRestaurantByID(int restaurantId) {
		restaurantDao.deleteById(restaurantId);
		
	}
	
	@Override
	@Transactional
		public Restaurant findById(int restaurantId) throws RestaurantNotFoundException {
			Optional<Restaurant> res=restaurantDao.findById(restaurantId);
			if(!(res.isPresent())) {
				throw new RestaurantNotFoundException("Not Found");
			}
			return res.get();
 
}
	
//	@Override
//	public List<MenuItems> getMenuItemsByRestaurant(String restaurantName) throws RestaurantNotFoundException {
//	    List<MenuItems> menuItems = menuItemsRepository.findByRestaurantName(restaurantName);
//	    if (menuItems.isEmpty()) {
//	        throw new RestaurantNotFoundException("Menu items for the restaurant '" + restaurantName + "' are not found!");
//	    }
//	    return menuItems;
//	}



    
    
    
}
 
 
