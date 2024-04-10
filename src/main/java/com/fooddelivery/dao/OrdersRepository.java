package com.fooddelivery.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fooddelivery.dto.DeliveryDriversDTO;
import com.fooddelivery.dto.OrdersDTO;
import com.fooddelivery.entity.DeliveryDrivers;
import com.fooddelivery.entity.Order;



@Repository
public interface OrdersRepository extends JpaRepository<Order, Integer> {

	List<Order> findByDeliveryDrivers(DeliveryDrivers driver);

	//List<Order> findByDeliveryDrivers(DeliveryDrivers driver);
}
