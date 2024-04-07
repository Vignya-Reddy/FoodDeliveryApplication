package com.fooddelivery.deliverydrivers.service;

import java.util.List;

import com.fooddelivery.exception.DeliveryDriversNotFoundException;
import com.fooddelivery.exception.DuplicateDeliveryDriversIDException;
import com.fooddelivery.model.DeliveryDrivers;
import com.fooddelivery.model.Order;

public interface DeliveryDriversService {
	int addDeliveryDrivers(DeliveryDrivers deliveryDrivers) throws DuplicateDeliveryDriversIDException;
    List<DeliveryDrivers> showDeliveryDrivers() throws DeliveryDriversNotFoundException;
	DeliveryDrivers findById(Integer driverId) throws DeliveryDriversNotFoundException;
	List<String> getOrdersByDriverId(int driverId);
	

}
