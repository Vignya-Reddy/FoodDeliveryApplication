package com.fooddelivery.dto;

import com.fooddelivery.entity.DeliveryDrivers;

public class DeliveryDriversDTO {
    private int deliveryDriversId;
    private String deliveryDriversName;
    private String deliveryDriversPhone;
    private String deliveryDriversVehicle;

    public int getDeliveryDriversId() {
        return deliveryDriversId;
    }

    public void setDeliveryDriversId(int deliveryDriversId) {
        this.deliveryDriversId = deliveryDriversId;
    }

    public String getDeliveryDriversName() {
        return deliveryDriversName;
    }

    public void setDeliveryDriversName(String deliveryDriversName) {
        this.deliveryDriversName = deliveryDriversName;
    }

    public String getDeliveryDriversPhone() {
        return deliveryDriversPhone;
    }

    public void setDeliveryDriversPhone(String deliveryDriversPhone) {
        this.deliveryDriversPhone = deliveryDriversPhone;
    }

    public String getDeliveryDriversVehicle() {
        return deliveryDriversVehicle;
    }

    public void setDeliveryDriversVehicle(String deliveryDriversVehicle) {
        this.deliveryDriversVehicle = deliveryDriversVehicle;
    }

    public static DeliveryDriversDTO fromDeliveryDrivers(DeliveryDrivers deliveryDriver) {
        DeliveryDriversDTO dto = new DeliveryDriversDTO();
        dto.setDeliveryDriversId(deliveryDriver.getDeliveryDriversId());
        dto.setDeliveryDriversName(deliveryDriver.getDeliveryDriversName());
        dto.setDeliveryDriversPhone(deliveryDriver.getDeliveryDriversPhone());
        dto.setDeliveryDriversVehicle(deliveryDriver.getDeliveryDriversVehicle());
        return dto;
    }
}
	