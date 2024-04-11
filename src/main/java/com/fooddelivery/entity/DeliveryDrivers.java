package com.fooddelivery.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "deliverydrivers")
public class DeliveryDrivers {
    @Id
    @Column(name="DRIVER_ID")
    private int deliveryDriversId;
    
    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name cannot exceed 50 characters")
    @Column(name="DRIVER_NAME")
    private String deliveryDriversName;
    
    @Digits(message="Number should contain 10 digits.", fraction = 0, integer = 10) 
    @Column(name="DRIVER_PHONE")
    private String deliveryDriversPhone;
    
    @Column(name="DRIVER_VEHICLE")
    private String deliveryDriversVehicle;

    public DeliveryDrivers() {
 
    }

    public DeliveryDrivers(int deliveryDriversId, String deliveryDriversName, String deliveryDriversPhone, String deliveryDriversVehicle) {
        this.deliveryDriversId = deliveryDriversId;
        this.deliveryDriversName = deliveryDriversName;
        this.deliveryDriversPhone = deliveryDriversPhone;
        this.deliveryDriversVehicle = deliveryDriversVehicle;
    }
    
    @OneToMany( fetch = FetchType.EAGER, cascade= CascadeType.ALL)

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


    
}

