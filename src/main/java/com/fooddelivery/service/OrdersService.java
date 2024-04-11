package com.fooddelivery.service;

import java.util.List;

import com.fooddelivery.dto.OrdersDTO;
import com.fooddelivery.entity.Order;
import com.fooddelivery.exception.CustomException;
import com.fooddelivery.exception.DuplicateOrderIDException;
import com.fooddelivery.exception.OrdersNotFoundException;

import javax.validation.Valid;


public interface OrdersService {
    int addOrders(@Valid Order orders) throws CustomException;
    List<Order> showOrders();
	void deleteOrderByID(int orderId);
	Order updateOrder(Order orders) throws CustomException;
	Order getOrderById(int orderId) throws CustomException;
	
}
