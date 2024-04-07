package com.fooddelivery.orders.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fooddelivery.model.DeliveryDrivers;
import com.fooddelivery.model.Order;



@Repository
public interface OrdersRepository extends JpaRepository<Order, Integer> {

	List<String> findByDeliveryDrivers(DeliveryDrivers driver);



}
