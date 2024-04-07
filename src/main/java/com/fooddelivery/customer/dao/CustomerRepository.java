package com.fooddelivery.customer.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fooddelivery.model.Customer;
@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Integer>{
}