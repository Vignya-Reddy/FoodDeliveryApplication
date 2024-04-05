package com.fooddelivery.model;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name="CUSTOMER_ID")
    private int customerId;
    
    @Column(name = "CUSTOMER_NAME")
    private String customerName;
    @Column(name = "CUSTOMER_EMAIL")
    private String customerEmail;
    
    //@Digits(message="Number should contain 10 digits.", fraction = 0, integer = 10) 
    @Column(name = "CUSTOMER_PHONE")
    private String customerPhone;
    
     
   
	public Customer(int customerId, String customerName, String customerEmail, String customerPhone) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerPhone = customerPhone;
	}
	
	public Customer() {
		
	}
	
	@OneToMany( fetch = FetchType.EAGER, cascade= CascadeType.ALL)

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	
	
    
	
   
   
   
   
}
 
