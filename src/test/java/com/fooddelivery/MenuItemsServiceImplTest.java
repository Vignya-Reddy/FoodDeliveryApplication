package com.fooddelivery;

import com.fooddelivery.exception.DuplicateItemIDException;
import com.fooddelivery.exception.ItemNotFoundException;
import com.fooddelivery.menuitems.dao.MenuItemsRepository;
import com.fooddelivery.menuitems.service.MenuItemsServiceImpl;
import com.fooddelivery.model.MenuItems;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MenuItemsServiceImplTest {

    @Mock
    private MenuItemsRepository menuItemsRepository;

    @InjectMocks
    private MenuItemsServiceImpl menuItemsService;

    @Test
    public void testShowMenuItems() {
        List<MenuItems> menuItems = new ArrayList<>();
        menuItems.add(new MenuItems(54, "Chocolate Ice Cream", "Icecream filled with toppings", 7.99));
        menuItems.add(new MenuItems(55, "Choco Lava", "Delicious cake filled with chocolate", 9.99));
        menuItems.add(new MenuItems(56, "Mango Ice Cream", "Icecream with Seasonal fruit and toppings", 6.99));
        when(menuItemsRepository.findAll()).thenReturn(menuItems);

        List<MenuItems> result = menuItemsService.showMenuItems();

        assertEquals(3, result.size());
        assertEquals("Chocolate Ice Cream", result.get(0).getItemName());
        assertEquals("Choco Lava", result.get(1).getItemName());
        assertEquals("Mango Ice Cream", result.get(2).getItemName());
        verify(menuItemsRepository).findAll();
    }

    @Test
    public void testAddMenuItems_Positive() throws DuplicateItemIDException {
        MenuItems items = new MenuItems(58, "Chicken Biryani", "Biryani with lot of spices", 13.67);
        when(menuItemsRepository.findById(58)).thenReturn(Optional.empty());
        when(menuItemsRepository.saveAndFlush(items)).thenReturn(items);

        int id = menuItemsService.addMenuItems(items);

        assertEquals(58, id);
        verify(menuItemsRepository).findById(58);
        verify(menuItemsRepository).saveAndFlush(items);
    }

    @Test
    public void testAddMenuItemsWithDuplicateItemID_Negative() {
        MenuItems existingItem = new MenuItems(54, "Chocolate Ice Cream", "Icecream filled with toppings", 7.99);
        when(menuItemsRepository.findById(54)).thenReturn(Optional.of(existingItem));

        assertThrows(DuplicateItemIDException.class, () -> {
            menuItemsService.addMenuItems(existingItem);
        });

        verify(menuItemsRepository, never()).saveAndFlush(existingItem);
    }

    
    @Test
	public void testUpdateMenuItem_Positive() throws ItemNotFoundException {
	    // Given
	    MenuItems existingItem = new MenuItems(51, "Miso Soup", "Japanese soup with tofu and seaweed", 4.99);
	    MenuItems updatedItem = new MenuItems(51, "Grilled Salmon Salad", "Fresh, flavorful, and satisfying.", 8.67);
	    when(menuItemsRepository.findById(51)).thenReturn(Optional.of(existingItem));
	    when(menuItemsRepository.save(existingItem)).thenReturn(existingItem);
 
	    // When
	    menuItemsService.updateMenuItem(updatedItem);
 
	    // Then
	    verify(menuItemsRepository).findById(51);
	    verify(menuItemsRepository).save(existingItem);
	    assertEquals(updatedItem.getItemName(), existingItem.getItemName());
        assertEquals(updatedItem.getItemDescription(), existingItem.getItemDescription());
        assertEquals(updatedItem.getItemPrice(), existingItem.getItemPrice(), 0.001);

	}
 
    
	@Test
	public void testUpdateMenuItem_Negative() {
	    // Given
	    int nonExistentMenuId = 99;
	    MenuItems updatedItem = new MenuItems(99, "Grilled Salmon Salad", "Fresh, flavorful, and satisfying.", 8.67);
	    when(menuItemsRepository.findById(nonExistentMenuId)).thenReturn(Optional.empty());
 
	    // Then
	    assertThrows(ItemNotFoundException.class, () -> {
	        // When
	        menuItemsService.updateMenuItem(updatedItem);
	    });
 
	    // Verify that findById method is called
	    verify(menuItemsRepository).findById(nonExistentMenuId);
	    // Verify that save method is not called
	    verify(menuItemsRepository, never()).save(any(MenuItems.class));
	}
	
	@Test
	public void testDeleteMenuItem_Positive() throws ItemNotFoundException {
	    // Given
	    int itemId = 51;
	    MenuItems existingItem = new MenuItems(51, "Miso Soup", "Japanese soup with tofu and seaweed", 4.99);
	    when(menuItemsRepository.findById(itemId)).thenReturn(Optional.of(existingItem));
 
	    // When
	    menuItemsService.deleteMenuItemByID(itemId);
 
	    // Then
	    verify(menuItemsRepository).findById(itemId);
	    verify(menuItemsRepository).deleteById(itemId);
	}
 
	@Test
	public void testDeleteMenuItem_Negative() {
	    // Given
	    int nonExistentMenuId = 99;
	    when(menuItemsRepository.findById(nonExistentMenuId)).thenReturn(Optional.empty());
 
	    // Then
	    assertThrows(ItemNotFoundException.class, () -> {
         menuItemsService.deleteMenuItemByID(nonExistentMenuId);
      });
 
	    // Verify that findById method is called
	    verify(menuItemsRepository).findById(nonExistentMenuId);
	    // Verify that deleteById method is not called
	    verify(menuItemsRepository, never()).deleteById(anyInt());
	}

}


 

