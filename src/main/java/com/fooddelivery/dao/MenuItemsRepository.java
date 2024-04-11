package com.fooddelivery.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fooddelivery.dto.MenuItemsDTO;
import com.fooddelivery.entity.MenuItems;

@Repository
public interface MenuItemsRepository extends JpaRepository<MenuItems, Integer>{
	
	@Query("SELECT mi FROM MenuItems mi WHERE mi.restaurant.restaurantId=:restaurantId")
	List<MenuItems> findByRestaurantId(@Param("restaurantId")int restaurantId);
	
	
	
}
