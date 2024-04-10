package com.fooddelivery.service;

import java.util.List;

import com.fooddelivery.dto.DeliveryDriversDTO;
import com.fooddelivery.dto.OrdersDTO;
import com.fooddelivery.entity.DeliveryDrivers;
import com.fooddelivery.entity.Order;
import com.fooddelivery.exception.CustomException;

public interface DeliveryDriversService {
	int addDeliveryDrivers(DeliveryDrivers deliveryDrivers) throws CustomException;
    List<DeliveryDrivers> showDeliveryDrivers() throws CustomException;
    DeliveryDrivers findById(int driverId) throws CustomException;
	List<Order> getOrdersByDriverId(int driverId) throws CustomException;

	
	
	

}
