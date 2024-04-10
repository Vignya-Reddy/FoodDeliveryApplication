package com.fooddelivery.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.fooddelivery.dto.OrdersDTO;
import com.fooddelivery.entity.Order;
import com.fooddelivery.exception.CustomException;
import com.fooddelivery.service.OrdersService;
import com.fooddelivery.util.SuccessResponse;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping(value = "/api/orders")
public class OrdersController {
    @Autowired
    OrdersService orderService;

    @GetMapping(produces = "application/json")
    List<OrdersDTO> showOrders() throws CustomException {
        List<Order> ordersList = orderService.showOrders();
        List<OrdersDTO> dtoList = new ArrayList<>();
        for (Order order : ordersList) {
            dtoList.add(OrdersDTO.fromOrders(order));
        }
        return dtoList;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<Object> addOrders(@Valid @RequestBody OrdersDTO ordersDTO) throws CustomException {
        Order order = new Order();
        order.setOrderId(ordersDTO.getOrderId());
        order.setOrderDate(ordersDTO.getOrderDate());
        order.setOrderStatus(ordersDTO.getOrderStatus());

        int orderId = orderService.addOrders(order);

        SuccessResponse successResponse = new SuccessResponse("Order added successfully", "200");
        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";

        return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
    }

    @PutMapping("/{orderId}/status")
    ResponseEntity<Object> updateOrder(@Valid @RequestBody OrdersDTO ordersDTO, @PathVariable("orderId") Integer orderId) throws CustomException {
        Order order = new Order();
        order.setOrderId(ordersDTO.getOrderId());
        order.setOrderDate(ordersDTO.getOrderDate());
        order.setOrderStatus(ordersDTO.getOrderStatus());

        Order updatedOrder = orderService.updateOrder(order);

        OrdersDTO updatedOrderDTO = OrdersDTO.fromOrders(updatedOrder);

        SuccessResponse successResponse = new SuccessResponse("Order updated successfully", "200");
        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";

        return ResponseEntity.ok(updatedOrderDTO);
    }

    @DeleteMapping("/{orderId}")
    ResponseEntity<Object> deleteOrder(@PathVariable("orderId") Integer orderId) {
        orderService.deleteOrderByID(orderId);

        SuccessResponse successResponse = new SuccessResponse("Order deleted successfully", "200");
        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";

        return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
    }
}
