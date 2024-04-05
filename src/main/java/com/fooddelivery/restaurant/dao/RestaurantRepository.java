package com.fooddelivery.restaurant.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fooddelivery.model.MenuItems;
import com.fooddelivery.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{
//	Optional<Restaurant> findByName(String restaurantName);
//	
//	@Query("SELECT m FROM MenuItems m WHERE m.restaurant.name = :restaurantName")
//	public List<MenuItems> findByRestaurantName(@Param("restaurantName") String restaurantName);

	
	
	
	
 
}
