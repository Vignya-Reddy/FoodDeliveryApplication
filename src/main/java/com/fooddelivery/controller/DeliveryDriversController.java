package com.fooddelivery.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.dto.DeliveryDriversDTO;
import com.fooddelivery.dto.OrdersDTO;
import com.fooddelivery.entity.DeliveryDrivers;
import com.fooddelivery.entity.Order;

import com.fooddelivery.exception.CustomException;
import com.fooddelivery.service.DeliveryDriversService;
import com.fooddelivery.service.OrdersService;
import com.fooddelivery.util.SuccessResponse;




@RestController
@Validated
@RequestMapping(value="/api/drivers")
public class DeliveryDriversController {
	@Autowired
    DeliveryDriversService deliveryDriversService;
	
	@Autowired
    OrdersService ordersService;
	
	
	
	@GetMapping(produces = "application/json")
    List<DeliveryDriversDTO> showDeliveryDrivers() throws CustomException {
        List<DeliveryDrivers> driList = deliveryDriversService.showDeliveryDrivers();
        List<DeliveryDriversDTO> dtoList = new ArrayList<>();
        for (DeliveryDrivers driver : driList) {
            dtoList.add(DeliveryDriversDTO.fromDeliveryDrivers(driver));
        }
        return dtoList;
    }
    
	@PostMapping(consumes = "application/json", produces = "application/json")
	ResponseEntity<Object> addDeliveryDrivers(@Valid @RequestBody DeliveryDriversDTO driverDTO) throws CustomException {
	    DeliveryDrivers driver = new DeliveryDrivers();
	    driver.setDeliveryDriversId(driverDTO.getDeliveryDriversId());
	    driver.setDeliveryDriversName(driverDTO.getDeliveryDriversName());
	    driver.setDeliveryDriversPhone(driverDTO.getDeliveryDriversPhone());
	    driver.setDeliveryDriversVehicle(driverDTO.getDeliveryDriversVehicle());
	    
	    int delId = deliveryDriversService.addDeliveryDrivers(driver);
	    SuccessResponse successResponse = new SuccessResponse("Driver added successfully", "200");
	    return ResponseEntity.status(HttpStatus.OK).body(Map.of("driverId", delId, "message", successResponse.getMessage(), "code", successResponse.getCode()));
	}

     
	 @GetMapping("/{driverId}")
	    ResponseEntity<Map<String, Object>> findByDriverId(@PathVariable("driverId") Integer driverId) throws CustomException {
	        DeliveryDrivers driver = deliveryDriversService.findById(driverId);
//	        if (driver == null) {
//	            throw new CustomException("Driver not present");
//	        }
	        DeliveryDriversDTO driverDTO = DeliveryDriversDTO.fromDeliveryDrivers(driver);
	        SuccessResponse successResponse = new SuccessResponse("Driver is fetched successfully", "200");
	        return ResponseEntity.status(HttpStatus.OK).body(Map.of("driver", driverDTO, "message", successResponse.getMessage(), "code", successResponse.getCode()));
	    }
    
    
	 @PutMapping("/api/orders/{orderId}/assignDriver/{driverId}")
	 public ResponseEntity<String> assignDriverToOrder(@PathVariable int orderId, @PathVariable int driverId) throws CustomException {
	     Order order = ordersService.getOrderById(orderId);
//	     if (order == null) {
//	         throw new CustomException("Order not found");
//	     }

	     DeliveryDrivers driver = deliveryDriversService.findById(driverId);
//	     if (driver == null) {
//	         throw new CustomException("Driver not found");
//	     }

	     order.setDeliveryDrivers(driver);
	     ordersService.updateOrder(order);

	     // Convert the updated order to DTO
	     OrdersDTO orderDTO = OrdersDTO.fromOrders(order);

	     SuccessResponse successResponse = new SuccessResponse("Driver assigned to the order successfully", "200");
	     return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\", \"order\": " + orderDTO + "}");
	 }

    
	 @GetMapping("/{driverId}/orders")
	 ResponseEntity<Map<String, Object>> getOrdersByDriverId(@PathVariable int driverId) throws  CustomException {
	     List<Order> orders = deliveryDriversService.getOrdersByDriverId(driverId);
	     List<OrdersDTO> orderDTOList = new ArrayList<>();
	     for (Order order : orders) {
	         orderDTOList.add(OrdersDTO.fromOrders(order));
	     }
	     
	     if (orderDTOList.isEmpty()) {
	         return ResponseEntity.noContent().build();
	     } else {
	         SuccessResponse successResponse = new SuccessResponse("Orders retrieved successfully", "200");
	         return ResponseEntity.status(HttpStatus.OK).body(Map.of("orders", orderDTOList, "message", successResponse.getMessage(), "code", successResponse.getCode()));
	     }
}
}
