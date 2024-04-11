package com.fooddelivery.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.dao.MenuItemsRepository;
import com.fooddelivery.dao.RatingsRepository;
import com.fooddelivery.dao.RestaurantRepository;
import com.fooddelivery.dto.MenuItemsDTO;
import com.fooddelivery.dto.RestaurantDTO;
import com.fooddelivery.entity.DeliveryAddress;
import com.fooddelivery.entity.MenuItems;
import com.fooddelivery.entity.Ratings;
import com.fooddelivery.entity.Restaurant;
import com.fooddelivery.exception.CustomException;
import com.fooddelivery.exception.DuplicateItemIDException;
import com.fooddelivery.exception.DuplicateRestaurantIDException;
import com.fooddelivery.exception.RestaurantNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Service
public class RestaurantServiceImpl implements RestaurantService {
 
    @Autowired
    RestaurantRepository restaurantDao;
    
    @Autowired
    MenuItemsRepository menuItemsDao;
    
    @Autowired
    RatingsRepository ratingsDao;
    
    @Override
    @Transactional
    public List<Restaurant> showRestaurants() {
        System.out.println("Service layer Restaurant called");
        return restaurantDao.findAll();
    }
 

    @Override
    @Transactional
    public int addRestaurants(Restaurant restaurant) throws CustomException {
    	Optional<Restaurant> res=restaurantDao.findById(restaurant.getRestaurantId());
		if(res.isPresent()) {			
				throw new CustomException("Restaurant with Id"+restaurant.getRestaurantId()+" already Exists");
			}
		restaurantDao.saveAndFlush(restaurant);
		return restaurant.getRestaurantId();
    }
    
//    @Transactional
//	@Override
//	public Restaurant updateRestaurant(Restaurant restaurant) {
//		
//		Optional<Restaurant> previousRestaurant= restaurantDao.findById(restaurant.getRestaurantId());
//		Restaurant resupdated=previousRestaurant.get();
//		
//		resupdated.setRestaurantName(restaurant.getRestaurantName()); 
//	
//		return resupdated;
//	}
    
    @Override
    @Transactional
    public Restaurant updateRestaurant(Restaurant restaurant) throws CustomException {
        Optional<Restaurant> previousRestaurant = restaurantDao.findById(restaurant.getRestaurantId());
        if (!previousRestaurant.isPresent()) {
            throw new CustomException("Restaurant with ID " + restaurant.getRestaurantId() + " not found");
        }
        Restaurant resUpdated = previousRestaurant.get();
        resUpdated.setRestaurantName(restaurant.getRestaurantName());
        
        // Save the updated restaurant entity
        return restaurantDao.save(resUpdated);
    }

    




    @Override
    @Transactional
    public void deleteRestaurantByID(int restaurantId) throws CustomException {
        Optional<Restaurant> restaurantOptional = restaurantDao.findById(restaurantId);
        if (!restaurantOptional.isPresent()) {
            throw new CustomException("Restaurant with ID " + restaurantId + " not found");
        }
        restaurantDao.deleteById(restaurantId);
    }

	
//	@Override
//	@Transactional
//		public Restaurant findById(int restaurantId) throws CustomException {
//			Optional<Restaurant> res=restaurantDao.findById(restaurantId);
//			if(!(res.isPresent())) {
//				throw new CustomException("Not Found");
//			}
//			return res.get();
//}
    
    @Override
    @Transactional
    public Restaurant findById(int restaurantId) throws CustomException {
        Optional<Restaurant> res = restaurantDao.findById(restaurantId);
        if (!res.isPresent()) {
            throw new CustomException("Restaurant with ID " + restaurantId + " not found");
        }
        return res.get();
    }
    
    
    
    
}
	
	
	
  
 
 
