
package com.fooddelivery.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "deliverydrivers")

public class DeliveryDrivers {
	@Id
    @Column(name="DRIVER_ID")
    private int deliveryDriversId;
    
    @NotBlank(message = "Name is required")
    @Size(max = 4, message = "Name cannot exceed 4 characters")
    @Column(name="DRIVER_NAME")
    private String deliveryDriversName;
    
    @Digits(message="Number should contain 10 digits.", fraction = 0, integer = 10) 
    @Column(name="DRIVER_PHONE")
    private String deliveryDriversPhone;
    @Column(name="DRIVER_VEHICLE")
    private String deliveryDriversVehicle;
    

    public DeliveryDrivers(int i, String string, String string2, String string3) {
		// TODO Auto-generated constructor stub
	}
	@OneToMany( fetch = FetchType.EAGER, cascade= CascadeType.ALL)
    //private List<MenuItems> items = new ArrayList<>();
    
	
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
