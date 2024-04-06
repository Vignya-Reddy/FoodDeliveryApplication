package com.fooddelivery.restaurant.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.exception.DuplicateItemIDException;
import com.fooddelivery.exception.DuplicateRestaurantIDException;
import com.fooddelivery.exception.RestaurantNotFoundException;
import com.fooddelivery.menuitems.dao.MenuItemsRepository;
import com.fooddelivery.model.DeliveryAddress;
import com.fooddelivery.model.MenuItems;
import com.fooddelivery.model.Ratings;
import com.fooddelivery.model.Restaurant;
import com.fooddelivery.restaurant.dao.RestaurantRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
public class RestaurantServiceImpl implements RestaurantService {
 
    @Autowired
    RestaurantRepository restaurantDao;
    
    @Autowired
    MenuItemsRepository menuItemsDao;
    
    @PersistenceContext
    private EntityManager entityManager;
    
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
	@Override
	@Transactional
	public List<MenuItems> getMenuItemsByRestaurantId(int restaurantId) {
		return menuItemsDao.findByRestaurantRestaurantId(restaurantId);
	}
	
	
	
	
	@Override
    @Transactional
    public List<String> getAllReviewsForRestaurant(int restaurantId) throws RestaurantNotFoundException {
        TypedQuery<String> query = entityManager.createQuery(
                "SELECT r.review FROM Ratings r WHERE r.restaurant.restaurantId = :restaurantId", String.class);
        query.setParameter("restaurantId", restaurantId);
        List<String> reviews = query.getResultList();
        if (reviews.isEmpty()) {
            throw new RestaurantNotFoundException("No reviews found for the restaurant ID: " + restaurantId);
        }
        return reviews;
    }
}
	
	
	
  
 
 
