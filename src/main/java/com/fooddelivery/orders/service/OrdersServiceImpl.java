package com.fooddelivery.orders.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.exception.DuplicateOrderIDException;
import com.fooddelivery.exception.OrdersNotFoundException;
import com.fooddelivery.model.DeliveryDrivers;
import com.fooddelivery.model.Order;
import com.fooddelivery.orders.dao.OrdersRepository;

import jakarta.transaction.Transactional;

@Service
public class OrdersServiceImpl implements OrdersService{
	 @Autowired
	    OrdersRepository orderDao;
	   
	    @Override
	    @Transactional
	    public int addOrders(Order orders) throws DuplicateOrderIDException {
	    	Optional<Order> order=orderDao.findById(orders.getOrderId());
			if(order.isPresent()) {			
					throw new DuplicateOrderIDException("Orders with Id"+orders.getOrderId()+" already Exists");
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
	    public Order updateOrder(Order orders) throws OrdersNotFoundException {
	        Optional<Order> optionalOrder = orderDao.findById(orders.getOrderId());
	        if (optionalOrder.isPresent()) {
	            Order existingOrder = optionalOrder.get();
	            existingOrder.setOrderStatus(orders.getOrderStatus());
	            // Save the updated order
	            return orderDao.save(existingOrder);
	        } else {
	            throw new OrdersNotFoundException("Order with ID " + orders.getOrderId() + " not found.");
	        }
	    }


		@Override
		@Transactional
		public void deleteOrderByID(int orderId) {
			orderDao.deleteById(orderId);
			
		}
		
		@Override
		@Transactional
	    public Order getOrderById(int id) throws OrdersNotFoundException {
	        return orderDao.findById(id)
	                .orElseThrow(() -> new OrdersNotFoundException("Order with id " + id + " not found"));
	    }
		
		
}
