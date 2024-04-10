package com.fooddelivery.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.fooddelivery.dao.DeliveryDriversRepository;
import com.fooddelivery.entity.DeliveryDrivers;
import com.fooddelivery.exception.CustomException;
import com.fooddelivery.exception.DeliveryDriversNotFoundException;
import com.fooddelivery.exception.DuplicateDeliveryDriversIDException;
import com.fooddelivery.service.DeliveryDriversServiceImpl;

@SpringBootTest
public class DeliveryDriversServiceImplTest {
	@Mock
    private DeliveryDriversRepository deliveryDriversRepository;

    @InjectMocks
    private DeliveryDriversServiceImpl deliveryDriversService;

    @Test
    public void testShowDeliveryDrivers() {
    	List<DeliveryDrivers> drivers = new ArrayList<>();
        drivers.add(new DeliveryDrivers(1, "Michael Johnson", "+11234567890", "Car"));
        drivers.add(new DeliveryDrivers(2, "Emily Smith", "+19876543210", "Bike"));
        drivers.add(new DeliveryDrivers(3, "David Williams", "+15551234567", "Scooter"));
        when(deliveryDriversRepository.findAll()).thenReturn(drivers);

        List<DeliveryDrivers> result = deliveryDriversService.showDeliveryDrivers();
        
        assertEquals(3, result.size());
        assertEquals("Michael Johnson", result.get(0).getDeliveryDriversName());
        assertEquals("Emily Smith", result.get(1).getDeliveryDriversName());
        assertEquals("David Williams", result.get(2).getDeliveryDriversName());
        verify(deliveryDriversRepository).findAll();
        }

     @Test
     public void testAddDeliveryDrivers_Positive() throws CustomException {
    	 DeliveryDrivers newDriver = new DeliveryDrivers(10,"Emma Taylor", "+112233441010", "Bike");
         when(deliveryDriversRepository.findById(10)).thenReturn(Optional.empty());
         when(deliveryDriversRepository.saveAndFlush(newDriver)).thenReturn(newDriver);

  
         int addedDriverId = deliveryDriversService.addDeliveryDrivers(newDriver);


         assertEquals(10, addedDriverId);
         verify(deliveryDriversRepository).findById(10);
         verify(deliveryDriversRepository).saveAndFlush(newDriver);
     }

     @Test
     public void testAddDeliveryDrivers_Negative() {
         
         DeliveryDrivers existingDriver = new DeliveryDrivers(10,"Emma Taylor", "+112233441010", "Bike");
         when(deliveryDriversRepository.findById(10)).thenReturn(Optional.of(existingDriver));
         assertThrows(DuplicateDeliveryDriversIDException.class, () -> {
             deliveryDriversService.addDeliveryDrivers(existingDriver);
         });
         verify(deliveryDriversRepository, never()).saveAndFlush(existingDriver);
     }
     
     @Test
     public void testFindById_ExistingDriver() throws CustomException {
         DeliveryDrivers existingDriver = new DeliveryDrivers(10, "Emma Taylor", "+112233441010", "Bike");
         when(deliveryDriversRepository.findById(10)).thenReturn(Optional.of(existingDriver));

         DeliveryDrivers result = deliveryDriversService.findById(10);

         assertNotNull(result);
         assertEquals(10, result.getDeliveryDriversId());
         assertEquals("Emma Taylor", result.getDeliveryDriversName());
         assertEquals("+112233441010", result.getDeliveryDriversPhone());
         assertEquals("Bike", result.getDeliveryDriversVehicle());
         verify(deliveryDriversRepository).findById(10);
     }

     @Test
     public void testFindById_NonExistingDriver() throws CustomException {
         when(deliveryDriversRepository.findById(100)).thenReturn(Optional.empty());

         DeliveryDrivers result = deliveryDriversService.findById(100);

         assertNull(result);
         verify(deliveryDriversRepository).findById(100);
     }
 }
