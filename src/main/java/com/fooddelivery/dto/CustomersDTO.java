package com.fooddelivery.dto;

import com.fooddelivery.entity.Customer;

public class CustomersDTO {
    private int customerId;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    

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
    
    public static CustomersDTO fromCustomer(Customer customer) {
        CustomersDTO customerDTO = new CustomersDTO();
        
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setCustomerName(customer.getCustomerName());
        customerDTO.setCustomerEmail(customer.getCustomerEmail());
        customerDTO.setCustomerPhone(customer.getCustomerPhone());
        
        return customerDTO;
    }
}
