package com.fooddelivery.entity;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name="CUSTOMER_ID")
    private int customerId;
    @NotBlank(message = "Name is required")
    @Size(max = 20, message = "Name cannot exceed 20 characters")
    @Column(name="CUSTOMER_NAME")
    private String customerName;
    @NotBlank(message = "EmailId is required")
    @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Please enter a valid email address")
    @Column(name="CUSTOMER_EMAIL")
    private String customerEmail;
    @NotBlank(message = "Phone Number is required")
    @Pattern(regexp="^\\+(?:[0-9] ?){6,14}[0-9]$", message="Invalid phone number format")
    @Column(name="CUSTOMER_PHONE")
    private String customerPhone;
     
    public Customer(int customerId,
			@NotBlank(message = "Name is required") @Size(max = 20, message = "Name cannot exceed 20 characters") String customerName,
			@NotBlank(message = "EmailId is required") @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Please enter a valid email address") String customerEmail,
			@NotBlank(message = "Phone Number is required") @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$", message = "Invalid phone number format") String customerPhone) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerPhone = customerPhone;
	}
    
    public Customer() {}
    
	@OneToMany(fetch = FetchType.EAGER, cascade= CascadeType.ALL)
	
	
	
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