package com.fooddelivery.service;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fooddelivery.dao.CustomerRepository;
import com.fooddelivery.entity.Customer;
import com.fooddelivery.exception.CustomException;
import com.fooddelivery.exception.CustomerNotFoundException;
import com.fooddelivery.exception.DuplicateCustomerIDException;
import com.fooddelivery.service.CustomerServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {
	@Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerServiceImpl customerService;
    @Test
    public void testShowMenuItems() {
        List<Customer> customer = new ArrayList<>();
        customer.add(new Customer(54, "Mark Antony", "markantony@example.com", "+112238885577"));
        customer.add(new Customer(55, "La Lisa", "lalalalisa@exapmle.com", "+66778899543"));
        customer.add(new Customer(56, "Monica", "monica@example.com", "+66778899543"));
        when(customerRepository.findAll()).thenReturn(customer);
        List<Customer> result = customerService.showCustomers();
        assertEquals(3, result.size());
        assertEquals("Mark Antony", result.get(0).getCustomerName());
        assertEquals("La Lisa", result.get(1).getCustomerName());
        assertEquals("Monica", result.get(2).getCustomerName());
        verify(customerRepository).findAll();
    }
    
    @Test
    public void testAddCustomers_Positive() throws CustomException {
    	Customer customer = new Customer(57, "John", "john@example.com", "+9977442297");
        when(customerRepository.findById(57)).thenReturn(Optional.empty());
        when(customerRepository.saveAndFlush(customer)).thenReturn(customer);
        int id = customerService.addCustomers(customer);
        assertEquals(57, id);
        verify(customerRepository).findById(57);
        verify(customerRepository).saveAndFlush(customer);
    }
    
    @Test
    public void testAddCustomerWithDuplicateItemID_Negative() {
    	Customer existingItem = new Customer(54, "Mark Antony", "markantony@example.com", "+112238885577");
        when(customerRepository.findById(54)).thenReturn(Optional.of(existingItem));
        assertThrows(DuplicateCustomerIDException.class, () -> {
            customerService.addCustomers(existingItem);
        });
        verify(customerRepository, never()).saveAndFlush(existingItem);
    }
    
    @Test
	public void testUpdateCustomer_Positive() throws CustomException {
	    Customer existingCustomer = new Customer(1, "John Smith", "john@example.com", "+1234567890");
	    Customer updatedCustomer = new Customer(1, "Micheal", "micheal@example.com", "+1234569990");
	    when(customerRepository.findById(1)).thenReturn(Optional.of(existingCustomer));
	    when(customerRepository.save(existingCustomer)).thenReturn(existingCustomer);
	    customerService.updateCustomer(updatedCustomer);
	    verify(customerRepository).findById(1);
	    verify(customerRepository).save(existingCustomer);
	    assertEquals("Micheal", existingCustomer.getCustomerName());
	    assertEquals("micheal@example.com", existingCustomer.getCustomerEmail());
	    assertEquals("+1234569990", existingCustomer.getCustomerPhone());
	}
	@Test
	public void testUpdateCustomer_Negative() {
	    int nonExistentCustomerId = 99;
	    Customer updatedCustomer = new Customer(99, "Paul", "paul@example.com", "+9988776654");
		when(customerRepository.findById(nonExistentCustomerId)).thenReturn(Optional.empty());
	    assertThrows(CustomerNotFoundException.class, () -> {
	    customerService.updateCustomer(updatedCustomer);
	    });
	    verify(customerRepository).findById(nonExistentCustomerId);
	    verify(customerRepository, never()).save(any(Customer.class));
	}
	
	@Test
	public void testDeleteCustomer_Positive() throws CustomException {
		int customerId=51;
		Customer existingCustomer = new Customer(customerId,"John Smith", "john@example.com", "+1234567890");
		when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));
	    customerService.deleteCustomerByID(51);
	    verify(customerRepository).findById(customerId);
	    verify(customerRepository).deleteById(customerId);
	}
	
	@Test
	public void testDeleteCustomer_Negative() {
	    int nonExistentCustomerId = 99;
	    when(customerRepository.findById(nonExistentCustomerId)).thenReturn(Optional.empty());
	    assertThrows(CustomerNotFoundException.class, () -> {
        customerService.deleteCustomerByID(nonExistentCustomerId);
        });
	    verify(customerRepository).findById(nonExistentCustomerId);
	    verify(customerRepository, never()).deleteById(Mockito.anyInt());
	}
	
	@Test
	public void testFindByCustomerId_positive() throws CustomException {
	    int existingCustomerId = 51;
	    Customer expectedCustomer = new Customer(existingCustomerId, "John Smith", "john@example.com", "+1234567890");
	    when(customerRepository.findById(existingCustomerId)).thenReturn(Optional.of(expectedCustomer));
	    Customer actualCustomer = customerService.findByCustomerId(existingCustomerId);
	    verify(customerRepository).findById(existingCustomerId);
	    assertEquals(expectedCustomer, actualCustomer);
	}
	
	@Test
	public void testFindByCustomerId_NotFound() throws CustomException {
	    int nonExistingCustomerId = 99; 
	    when(customerRepository.findById(nonExistingCustomerId)).thenReturn(Optional.empty());
	    try {
	    customerService.findByCustomerId(nonExistingCustomerId);
	    fail("Expected CustomerNotFoundException");
	    } catch (Exception e) { 
	    assertTrue(e instanceof CustomerNotFoundException);
	  } 
	}  
}




