package com.fooddelivery.restaurant.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.fooddelivery.model.DeliveryAddress;
import com.fooddelivery.model.MenuItems;
import com.fooddelivery.model.Ratings;
import com.fooddelivery.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
	
	List<Restaurant> findByRestaurantId(int restaurantId);

}
