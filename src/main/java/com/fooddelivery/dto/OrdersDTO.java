package com.fooddelivery.dto;

import com.fooddelivery.entity.Order;


public class OrdersDTO {
    private int orderId;
    private String orderDate;
    private String orderStatus;
    private DeliveryDriversDTO deliveryDriver;
    private RestaurantDTO restaurant;
    private CustomersDTO customer;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public DeliveryDriversDTO getDeliveryDriver() {
        return deliveryDriver;
    }

    public void setDeliveryDriver(DeliveryDriversDTO deliveryDriver) {
        this.deliveryDriver = deliveryDriver;
    }

    public RestaurantDTO getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDTO restaurant) {
        this.restaurant = restaurant;
    }

    public CustomersDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomersDTO customer) {
        this.customer = customer;
    }

    public static OrdersDTO fromOrders(Order order) {
        OrdersDTO dto = new OrdersDTO();
        dto.setOrderId(order.getOrderId());
        dto.setOrderDate(order.getOrderDate());
        dto.setOrderStatus(order.getOrderStatus());
        dto.setDeliveryDriver(DeliveryDriversDTO.fromDeliveryDrivers(order.getDeliveryDrivers()));
        dto.setRestaurant(RestaurantDTO.fromRestaurant(order.getRestaurant()));
        dto.setCustomer(CustomersDTO.fromCustomer(order.getCustomer()));
        return dto;
    }
}
