package com.fooddelivery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fooddelivery.dto.CustomersDTO;
import com.fooddelivery.entity.Customer;
import com.fooddelivery.exception.CustomException;
import com.fooddelivery.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
 
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
 
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerControllerTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @Autowired
    private CustomerService customerService;
 
    private String token = "your-auth-token";
 
    @Test
    @Order(1)
    void testShowCustomers() throws Exception {
        // Given
        List<CustomersDTO> customersDTOList = new ArrayList<>();
        customersDTOList.add(new CustomersDTO(1, "John", "john@example.com", "1234567890"));
        customersDTOList.add(new CustomersDTO(2, "Alice", "alice@example.com", "9876543210"));
        
        List<Customer> customersList = customersDTOList.stream()
            .map(dto -> new Customer(dto.getCustomerId(), dto.getCustomerName(), dto.getCustomerEmail(), dto.getCustomerPhone()))
            .collect(Collectors.toList());
        
        when(customerService.showCustomers()).thenReturn(customersList);
 
        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
 
    @Test
    @Order(2)
    void testFindByCustomerId() throws Exception {
        // Given
        int customerId = 1;
        Customer customer = new Customer(customerId, "John", "john@example.com", "1234567890");
        when(customerService.findByCustomerId(customerId)).thenReturn(customer);
 
        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{customerId}", customerId)
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
 
    @Test
    @Order(3)
    void testAddCustomers() throws Exception {
        // Given
        CustomersDTO customerDTO = new CustomersDTO(1, "John", "john@example.com", "1234567890");
 
        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(customerDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
 
    @Test
    @Order(4)
    void testUpdateCustomer() throws Exception {
        // Given
        CustomersDTO customerDTO = new CustomersDTO(1, "John", "john@example.com", "1234567890");
 
        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.put("/users/{customerId}", customerDTO.getCustomerId())
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(customerDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
 
    @Test
    @Order(5)
    void testDeleteCustomer() throws Exception {
        // Given
        int customerId = 1;
 
        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{customerId}", customerId)
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}