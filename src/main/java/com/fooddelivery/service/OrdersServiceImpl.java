package com.fooddelivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.dao.OrdersRepository;
import com.fooddelivery.dto.OrdersDTO;
import com.fooddelivery.entity.DeliveryDrivers;
import com.fooddelivery.entity.Order;
import com.fooddelivery.exception.CustomException;
import com.fooddelivery.exception.DuplicateOrderIDException;
import com.fooddelivery.exception.OrdersNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class OrdersServiceImpl implements OrdersService{
	 @Autowired
	    OrdersRepository orderDao;
	   
	    @Override
	    @Transactional
	    public int addOrders(Order orders) throws CustomException {
	    	Optional<Order> order=orderDao.findById(orders.getOrderId());
			if(order.isPresent()) {			
					throw new CustomException("Orders with Id"+orders.getOrderId()+" already Exists");
				}
			orderDao.saveAndFlush(orders);
			return orders.getOrderId();
	    }
	 
	    @Override
	    @Transactional
	    public List<Order> showOrders() {
	        System.out.println("Service layer Orders called");
	        return orderDao.findAll();
	    }

	    @Override
	    @Transactional
	    public Order updateOrder(Order orders) throws CustomException {
	        Optional<Order> optionalOrder = orderDao.findById(orders.getOrderId());
	        if (optionalOrder.isPresent()) {
	        	Order existingOrder = optionalOrder.get();
	            existingOrder.setOrderStatus(orders.getOrderStatus());
	            // Save the updated order
	            return orderDao.save(existingOrder);
	        } else {
	            throw new CustomException("Order with ID " + orders.getOrderId() + " not found.");
	        }
	    }


		@Override
		@Transactional
		public void deleteOrderByID(int orderId) {
			orderDao.deleteById(orderId);
			
		}
		
		@Override
		@Transactional
	    public Order  getOrderById(int id) throws CustomException {
	        return orderDao.findById(id)
	                .orElseThrow(() -> new CustomException("Order with id " + id + " not found"));
	    }
		
		
}
