
package com.fooddelivery.deliverydrivers.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.deliverydrivers.dao.DeliveryDriversRepository;
import com.fooddelivery.exception.DeliveryDriversNotFoundException;
import com.fooddelivery.exception.DuplicateDeliveryDriversIDException;
import com.fooddelivery.exception.OrdersNotFoundException;
import com.fooddelivery.exception.RestaurantNotFoundException;
import com.fooddelivery.model.DeliveryDrivers;
import com.fooddelivery.model.Order;
import com.fooddelivery.orders.dao.OrdersRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
public class DeliveryDriversServiceImpl implements DeliveryDriversService {
	@PersistenceContext
    private EntityManager entityManager;
    @Autowired
    DeliveryDriversRepository deliveryDriversDao;
    @Autowired
    OrdersRepository orderDao;

    @Override
    @Transactional
    public List<DeliveryDrivers> showDeliveryDrivers() {
        return deliveryDriversDao.findAll();
    }

    @Override
    @Transactional
    public int addDeliveryDrivers(DeliveryDrivers deliveryDrivers) throws DuplicateDeliveryDriversIDException {
        Optional<DeliveryDrivers> existingDriver = deliveryDriversDao.findById(deliveryDrivers.getDeliveryDriversId());
        if (existingDriver.isPresent()) {
            throw new DuplicateDeliveryDriversIDException("Delivery driver with ID " + deliveryDrivers.getDeliveryDriversId() + " already exists.");
        } else {
            DeliveryDrivers savedDriver = deliveryDriversDao.saveAndFlush(deliveryDrivers);
            return savedDriver.getDeliveryDriversId();
        }
    }

   
//    @Override
//    @Transactional
//    public List<String> getAllOrdersForDriver(int driverId) throws DeliveryDriversNotFoundException {
//        TypedQuery<String> query = entityManager.createQuery(
//                "SELECT o.order FROM orders o WHERE d.deliveryDrivers.driverId = :driverId", String.class);
//        query.setParameter("driverId", driverId);
//        List<String> orders = query.getResultList();
//        if (orders.isEmpty()) {
//            throw new DeliveryDriversNotFoundException("No orders for delivery driver with  ID: " + driverId);
//        }
//        return orders;
//    }
    
 
    @Override
    @Transactional
    public DeliveryDrivers findById(int driverId) throws DeliveryDriversNotFoundException {
        return deliveryDriversDao.findById(driverId)
                .orElseThrow(() -> new DeliveryDriversNotFoundException("Delivery driver with id " + driverId + " not found"));
    }
    
    @Override
    @Transactional
    public List<Order> getOrdersByDriverId(int driverId) throws OrdersNotFoundException, DeliveryDriversNotFoundException {
        // Find delivery driver by ID
        DeliveryDrivers driver = findById(driverId);
        
        // Retrieve orders associated with the delivery driver
        List<Order> orders = orderDao.findByDeliveryDrivers(driver);
        
        // Check if orders are empty
        if (orders.isEmpty()) {
            throw new OrdersNotFoundException("No orders found for delivery driver with ID: " + driverId);
        }
        
        return orders;
    }


    }


