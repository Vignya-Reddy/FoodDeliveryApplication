package com.fooddelivery.model;



import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


@Entity
@Table(name = "deliveryaddresses")
public class DeliveryAddress implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name= "menuitems_seq",sequenceName = "menuitems_seq",allocationSize=1)
    @Column(name="ADDRESS_ID")
    private int addressId;
    
    @Column(name="ADDRESS_LINE1")
    private String addressLine1;
    @Column(name="ADDRESS_LINE2")
    private String addressLine2;
    
    @Column(name="CITY")
    private String city;
    @Column(name="STATE")
    private String state;
    @Column(name="POSTAL_CODE")
    private String postalCode;
    
	public DeliveryAddress(int addressId, String addressLine1, String addressLine2, String city, String state,
			String postalCode, Restaurant restaurant) {
		super();
		this.addressId = addressId;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		
	}
	
	public DeliveryAddress() {
		
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
	
   
    public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
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


	@Override
    public String toString() {
        return "DeliveryAddress [addressId=" + addressId + ", addressline1=" + addressLine1 + ", addressline2 =" + addressLine2 + ", city =" + city +  ", state =" + state +  ", postalcode =" + postalCode +"]";
    }
 
 
}
 
