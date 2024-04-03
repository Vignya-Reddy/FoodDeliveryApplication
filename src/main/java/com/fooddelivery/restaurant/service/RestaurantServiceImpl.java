package com.fooddelivery.restaurant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.model.Restaurant;
import com.fooddelivery.restaurant.dao.RestaurantRepository;

import jakarta.transaction.Transactional;

@Service
public class RestaurantServiceImpl implements RestaurantService {
 
    @Autowired
    RestaurantRepository restaurantDao;
   
   
    @Override
    @Transactional
    public List<Restaurant> showRestaurants() {
        // TODO Auto-generated method stub
        System.out.println("Service layer Restaurant called");
        return restaurantDao.findAll();
    }
 
 
  
    @Override
    @Transactional
    public int addRestaurants(Restaurant restaurant){
       // Optional<Restaurant> res=restaurantDao.findById(restaurant.getRestaurantId());
        restaurantDao.saveAndFlush(restaurant);
        return restaurant.getRestaurantId();
    }
    
    
    
}
 
 
