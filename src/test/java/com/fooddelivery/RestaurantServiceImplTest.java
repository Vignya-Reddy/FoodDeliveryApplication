package com.fooddelivery;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.fooddelivery.exception.DuplicateRestaurantIDException;
import com.fooddelivery.model.Restaurant;
import com.fooddelivery.restaurant.dao.RestaurantRepository;
import com.fooddelivery.restaurant.service.RestaurantServiceImpl;

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
        assertEquals(3, result.size(), "The size of the result list should be 3");
        assertEquals("Bella Italia", result.get(0).getRestaurantName(), "The name of the first restaurant should be 'Bella Italia'");
        assertEquals("Tokyo Sushi Bar", result.get(1).getRestaurantName(), "The name of the second restaurant should be 'Tokyo Sushi Bar'");
        assertEquals("Le Bistro Français", result.get(2).getRestaurantName(), "The name of the third restaurant should be 'Le Bistro Français'");

        verify(restaurantRepository).findAll();
    }

    @Test
    public void testAddRestaurants_Positive() throws DuplicateRestaurantIDException {
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
        assertThrows(DuplicateRestaurantIDException.class, () -> {
            restaurantService.addRestaurants(existingRestaurant);
        });

        // Verify that saveAndFlush method is not called
        verify(restaurantRepository, never()).saveAndFlush(existingRestaurant);
    }
}
