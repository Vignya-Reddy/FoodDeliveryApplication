package com.fooddelivery.orders.service;

import java.util.List;

import com.fooddelivery.exception.DuplicateOrderIDException;
import com.fooddelivery.exception.OrdersNotFoundException;
import com.fooddelivery.model.Order;


public interface OrdersService {
    int addOrders(Order orders) throws DuplicateOrderIDException;
    List<Order> showOrders();
	void deleteOrderByID(int orderId);
	Order updateOrder(Order orders) throws OrdersNotFoundException;


	

}
