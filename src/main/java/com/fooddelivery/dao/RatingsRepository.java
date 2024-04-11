package com.fooddelivery.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fooddelivery.entity.Ratings;

@Repository
public interface RatingsRepository extends JpaRepository<Ratings, Integer> {
	@Query("SELECT r FROM Ratings r WHERE r.restaurant.id = :restaurantId")
	List<Ratings> findByRestaurantId(@Param("restaurantId") int restaurantId);
}