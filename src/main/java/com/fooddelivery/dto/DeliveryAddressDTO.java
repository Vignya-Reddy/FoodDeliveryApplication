package com.fooddelivery.dto;

import java.time.LocalDateTime;

import com.fooddelivery.entity.DeliveryAddress;

public class DeliveryAddressDTO {
    private int addressId;
    private CustomersDTO customer;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private LocalDateTime createdAt;

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public CustomersDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomersDTO customer) {
        this.customer = customer;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static DeliveryAddressDTO fromDeliveryAddress(DeliveryAddress deliveryAddress) {
        DeliveryAddressDTO dto = new DeliveryAddressDTO();
        dto.setAddressId(deliveryAddress.getAddressId());
        dto.setAddressLine1(deliveryAddress.getAddressLine1());
        dto.setAddressLine2(deliveryAddress.getAddressLine2());
        dto.setCity(deliveryAddress.getCity());
        dto.setState(deliveryAddress.getState());
        dto.setPostalCode(deliveryAddress.getPostalCode());
        dto.setCustomer(CustomersDTO.fromCustomer(deliveryAddress.getCustomer()));
        return dto;
    }
}
