
package com.fooddelivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.dao.DeliveryDriversRepository;
import com.fooddelivery.dao.OrdersRepository;
import com.fooddelivery.dto.DeliveryDriversDTO;
import com.fooddelivery.dto.OrdersDTO;
import com.fooddelivery.entity.DeliveryDrivers;
import com.fooddelivery.entity.Order;
import com.fooddelivery.exception.CustomException;
import com.fooddelivery.exception.DeliveryDriversNotFoundException;
import com.fooddelivery.exception.DuplicateDeliveryDriversIDException;
import com.fooddelivery.exception.OrdersNotFoundException;
import com.fooddelivery.exception.RestaurantNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

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
    public int addDeliveryDrivers(DeliveryDrivers deliveryDrivers) throws CustomException {
        Optional<DeliveryDrivers> existingDriver = deliveryDriversDao.findById(deliveryDrivers.getDeliveryDriversId());
        if (existingDriver.isPresent()) {
            throw new CustomException("Delivery driver with ID " + deliveryDrivers.getDeliveryDriversId() + " already exists.");
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
    public DeliveryDrivers findById(int driverId) throws CustomException {
        return deliveryDriversDao.findById(driverId)
                .orElseThrow(() -> new CustomException("Delivery driver with id " + driverId + " not found"));
    }
    
    @Override
    @Transactional
    public List<Order> getOrdersByDriverId(int driverId) throws CustomException {
    	DeliveryDrivers driver = findById(driverId);
        List<Order> orders = orderDao.findByDeliveryDrivers(driver);
        if (orders.isEmpty()) {
            throw new CustomException("No orders found for delivery driver with ID: " + driverId);
        }
        return orders;
    }
    }


