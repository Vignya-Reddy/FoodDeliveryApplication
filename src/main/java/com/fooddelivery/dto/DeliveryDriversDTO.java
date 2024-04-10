package com.fooddelivery.dto;

import com.fooddelivery.entity.DeliveryDrivers;

public class DeliveryDriversDTO {
    private int driverId;
    private String driverName;
    private String driverPhone;
    private String driverVehicle;

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getDriverVehicle() {
        return driverVehicle;
    }

    public void setDriverVehicle(String driverVehicle) {
        this.driverVehicle = driverVehicle;
    }

    public static DeliveryDriversDTO fromDeliveryDrivers(DeliveryDrivers deliveryDriver) {
        DeliveryDriversDTO dto = new DeliveryDriversDTO();
        dto.setDriverId(deliveryDriver.getDeliveryDriversId());
        dto.setDriverName(deliveryDriver.getDeliveryDriversName());
        dto.setDriverPhone(deliveryDriver.getDeliveryDriversPhone());
        dto.setDriverVehicle(deliveryDriver.getDeliveryDriversVehicle());
        return dto;
    }
}
	