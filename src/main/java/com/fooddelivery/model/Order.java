package com.fooddelivery.model;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
//@SequenceGenerator(name = "order_seq", sequenceName = "order_sequence", allocationSize = 1)
//@SequenceGenerator(name="order_gen", sequenceName="order_seq")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="ORDER_ID")
    private int orderId;
    
    @NotNull(message = "Name is required")
    @Column(name="ORDER_DATE")
    private @NotNull(message = "Name is required") String orderDate;

    @Column(name="ORDER_STATUS",length = 255 )
    private String orderStatus;

    
    public Order(int orderId, @NotNull(message = "Name is required") String orderDate, String orderStatus) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
	}
    public Order()
    {}
    

	@ManyToOne
    @JoinColumn(name="driver_id")
    private DeliveryDrivers deliveryDrivers;

    @ManyToOne 
    @JoinColumn(name="restaurant_id") 
    private Restaurant restaurant; 
    
	public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    
    @ManyToOne 
    @JoinColumn(name="customer_id") 
    private Customer customer; 
    
    public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public DeliveryDrivers getDeliveryDrivers() {
        return deliveryDrivers;
    }

    public void setDeliveryDrivers(DeliveryDrivers deliveryDrivers) {
        this.deliveryDrivers = deliveryDrivers;
    }

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

    @Override
    public String toString() {
        return "Orders [orderId=" + orderId + ", order date=" + orderDate + ", order status=" + orderStatus+ ", restaurant=" + restaurant + "]";
    }

	
}
