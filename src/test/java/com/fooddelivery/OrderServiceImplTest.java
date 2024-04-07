
package com.fooddelivery;

import com.fooddelivery.exception.DuplicateOrderIDException;
import com.fooddelivery.exception.OrdersNotFoundException;
import com.fooddelivery.model.Order;
import com.fooddelivery.orders.dao.OrdersRepository;
import com.fooddelivery.orders.service.OrdersServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    private OrdersRepository orderRepository;

    @InjectMocks
    private OrdersServiceImpl orderService;

    @Test
    public void testShowOrders() {
        // Given
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(55, "2024-01-05 16:00:00", "Delivered"));
        orders.add(new Order(56, "2024-01-06 17:00:00", "Pending"));
        orders.add(new Order(57, "2024-01-07 18:00:00", "Delivered"));

        when(orderRepository.findAll()).thenReturn(orders);

        // When
        List<Order> result = orderService.showOrders();

        // Then
        assertEquals(3, result.size());
        assertEquals("Delivered", result.get(0).getOrderStatus());
        assertEquals("Pending", result.get(1).getOrderStatus());
        assertEquals("Delivered", result.get(2).getOrderStatus());

        verify(orderRepository).findAll();
    }

    @Test
    public void testAddOrder_Positive() throws DuplicateOrderIDException {
        // Given
        Order orders = new Order(5, "2024-01-05 16:00:00","Delivered");
        when(orderRepository.findById(5)).thenReturn(Optional.empty());
        when(orderRepository.saveAndFlush(orders)).thenReturn(orders);

        // When
        int id = orderService.addOrders(orders);

        // Then
        assertEquals(5, id);
        verify(orderRepository).findById(5);
        verify(orderRepository).saveAndFlush(orders);
    }

    @Test
    public void testAddOrderWithDuplicateOrderID_Negative() {
        // Given
        Order existingOrder = new Order(55, "2024-01-05 16:00:00", "Delivered");
        when(orderRepository.findById(55)).thenReturn(Optional.of(existingOrder));

        // Then
        assertThrows(DuplicateOrderIDException.class, () -> {
            // When
            orderService.addOrders(existingOrder);
        });

        verify(orderRepository, never()).saveAndFlush(existingOrder);
    }


    @Test
    public void testUpdateOrder_Positive() throws OrdersNotFoundException {
        // Given
        Order existingOrder = new Order(50, "2024-02-19 13:00:00", "Delivered");
        Order updatedOrder = new Order(50, "2024-02-19 13:00:00", "Pending");
        when(orderRepository.findById(50)).thenReturn(Optional.of(existingOrder));
        when(orderRepository.save(existingOrder)).thenReturn(updatedOrder);

        // When
        Order result = orderService.updateOrder(updatedOrder);

        // Then
        verify(orderRepository).findById(50);
        verify(orderRepository).save(existingOrder); // Verify save with existingOrder
        assertEquals(updatedOrder.getOrderStatus(), result.getOrderStatus());
    }

    
    @Test
    public void testUpdateOrder_Negative() {
        // Given
        int nonExistentOrderId = 57;
        Order updatedOrder = new Order(57, "2024-02-20 15:00:00", "Pending");
        when(orderRepository.findById(nonExistentOrderId)).thenReturn(Optional.empty());

        // Then
        assertThrows(OrdersNotFoundException.class, () -> {
            // When
            orderService.updateOrder(updatedOrder);
        });

        // Verify that findById method is called
        verify(orderRepository).findById(nonExistentOrderId);
        // Verify that save method is not called
        verify(orderRepository, never()).save(any(Order.class));
    }

    @Test
    public void testDeleteOrder_Positive() {
        // Arrange
        int orderId = 48;

        // Act
        orderService.deleteOrderByID(orderId);

        // Assert
        verify(orderRepository, times(1)).deleteById(orderId);
    }

    @Test
    public void testDeleteOrder_Negative() {
        // Arrange
        int nonExistingOrderId = 48;

        // Set up mock behavior
        doThrow(new RuntimeException("Order not found")).when(orderRepository).deleteById(nonExistingOrderId);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            orderService.deleteOrderByID(nonExistingOrderId);
        });
    }

}
    

