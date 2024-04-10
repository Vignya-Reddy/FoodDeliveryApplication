package com.fooddelivery.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fooddelivery.dto.RestaurantDTO;
import com.fooddelivery.entity.DeliveryAddress;
import com.fooddelivery.entity.MenuItems;
import com.fooddelivery.entity.Ratings;
import com.fooddelivery.entity.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
	
	List<Restaurant> findByRestaurantId(int restaurantId);

}
