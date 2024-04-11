package com.fooddelivery.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.fooddelivery.dao.RestaurantRepository;
import com.fooddelivery.entity.Restaurant;
import com.fooddelivery.exception.CustomException;

 

 
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RestaurantServiceImplTest {
 
    @Mock
    private RestaurantRepository restaurantRepository;
 
    @InjectMocks
    private RestaurantServiceImpl restaurantService;
 
    
    @Test
    public void testShowRestaurants() {
        // Arrange
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(53, "Bella Italia", "123 Main Street", "+91234567"));
        restaurants.add(new Restaurant(54, "Tokyo Sushi Bar", "456 Elm Street", "+91234897"));
        restaurants.add(new Restaurant(55, "Le Bistro Français", "789 Oak Avenue", "+91234667"));
        when(restaurantRepository.findAll()).thenReturn(restaurants);
 
        // Act
        List<Restaurant> result = restaurantService.showRestaurants();
 
        // Assert
        assertEquals(3, result.size());
        assertEquals("Bella Italia", result.get(0).getRestaurantName());
        assertEquals("Tokyo Sushi Bar", result.get(1).getRestaurantName());
        assertEquals("Le Bistro Français", result.get(2).getRestaurantName());
 
        verify(restaurantRepository).findAll();
    }
 
 
    @Test
    public void testAddRestaurants_Positive() throws CustomException {
        // Arrange
        Restaurant restaurant = new Restaurant(56, "El Rancho Mexican Grill", "234 Maple Road", "+23456678");
        when(restaurantRepository.findById(56)).thenReturn(Optional.empty());
        when(restaurantRepository.saveAndFlush(restaurant)).thenReturn(restaurant);
 
        // Act
        int id = restaurantService.addRestaurants(restaurant);
 
        // Assert
        assertEquals(56, id);
        verify(restaurantRepository).findById(56);
        verify(restaurantRepository).saveAndFlush(restaurant);
    }
 
    @Test
    public void testAddRestaurants_Negative() {
        // Arrange
        Restaurant existingRestaurant = new Restaurant(53, "Bella Italia", "123 Main Street", "+91234567");
        when(restaurantRepository.findById(53)).thenReturn(Optional.of(existingRestaurant));
 
        // Act & Assert
        assertThrows(CustomException.class, () -> {
            restaurantService.addRestaurants(existingRestaurant);
        });
 
        // Verify that saveAndFlush method is not called
        verify(restaurantRepository, never()).saveAndFlush(existingRestaurant);
    }
    
    @Test
    public void testUpdateRestaurant_Positive() throws CustomException {
        // Given
        Restaurant existingRes = new Restaurant(53, "Bella Italia", "123 Main Street", "+91234567");
        Restaurant updatedRes = new Restaurant(53, "Tokyo Sushi Bar", "456 Elm Street", "+91234897");
        
        // Arrange mock to return the existing restaurant when findById is called
        when(restaurantRepository.findById(53)).thenReturn(Optional.of(existingRes));
        // Arrange mock to return the updated restaurant when save is called
        when(restaurantRepository.save(existingRes)).thenReturn(updatedRes);
        
        // When
        Restaurant result = restaurantService.updateRestaurant(updatedRes);

        // Then
        // Verify that findById method is called with the provided ID
        verify(restaurantRepository).findById(53);
        // Verify that save method is called with the updated restaurant
        verify(restaurantRepository).save(existingRes);
        
        // Assert that the returned restaurant object is the same as the updatedRes
        assertEquals(updatedRes, result);
    }


    @Test
    public void testUpdateRestaurant_Negative() {
        // Given
        int nonExistentResId = 99;
        Restaurant updatedRes = new  Restaurant(99, "Bella Italia", "123 Main Street", "+91234567");
        when(restaurantRepository.findById(nonExistentResId)).thenReturn(Optional.empty());

        // Then
        CustomException exception = assertThrows(CustomException.class, () -> {
            // When
            restaurantService.updateRestaurant(updatedRes);
        });

        assertEquals("Restaurant with ID " + nonExistentResId + " not found", exception.getMessage());

        // Verify that findById method is called
        verify(restaurantRepository).findById(nonExistentResId);
        // Verify that save method is not called
        verify(restaurantRepository, never()).save(any(Restaurant.class));
    }


 
	@Test
	public void testDeleteRestaurant_Positive() throws CustomException {
	    // Given
	    int resId = 53;
	    // Arrange mock to return an optional containing the restaurant
	    when(restaurantRepository.findById(resId)).thenReturn(Optional.of(new Restaurant(resId, "Bella Italia", "123 Main Street", "+91234567")));

	    // When
	    restaurantService.deleteRestaurantByID(resId);

	    // Then
	    // Verify that deleteById method is called with the provided ID
	    verify(restaurantRepository).deleteById(resId);
	}

	
	@Test
	public void testDeleteRestaurant_Negative() {
	    // Given
	    int nonExistentResId = 99;
	    when(restaurantRepository.findById(nonExistentResId)).thenReturn(Optional.empty());
	 
	    // Then
	    assertThrows(CustomException.class, () -> {
	        // When
	        restaurantService.deleteRestaurantByID(nonExistentResId);
	    });
	 
	    // Verify that findById method is called
	    verify(restaurantRepository).findById(nonExistentResId);
	    // Verify that deleteById method is not called
	    verify(restaurantRepository, never()).deleteById(anyInt());
	}

}
 
 



