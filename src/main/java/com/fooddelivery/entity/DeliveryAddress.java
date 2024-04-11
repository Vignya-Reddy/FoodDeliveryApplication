package com.fooddelivery.entity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = " deliveryaddresses")
@SequenceGenerator(name= "deliveryaddresses_seq",sequenceName = "deliveryaddresses_seq")
public class DeliveryAddress implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="deliveryaddresses_seq")
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
 
    @ManyToOne
    @JoinColumn(name="customer_id")
    
    private Customer customer;
 
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	    public String toString() {
	        return "DeliveryAddress [address_Id=" + addressId + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", city=" + city+  ", state=" + state + " postalCode=" + postalCode+" ]";
	    }
}