package com.fooddelivery;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.fooddelivery.exception.DuplicateItemIDException;
import com.fooddelivery.menuitems.dao.MenuItemsRepository;
import com.fooddelivery.menuitems.service.MenuItemsServiceImpl;
import com.fooddelivery.model.MenuItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
		menuItems.add(new MenuItems(54, "Chocolate Ice Cream", "Icecream filled with toppings",7.99));
		menuItems.add(new MenuItems(55, "Choco Lava", "Delicious cake filled with chocolate",9.99));
		menuItems.add(new MenuItems(56, "Mango Ice Cream", "Icecream with Seasonal fruit and toppings",6.99));
		when(menuItemsRepository.findAll()).thenReturn(menuItems);
 
		List<MenuItems> result = menuItemsService.showMenuItems();
 
		assertEquals(3, result.size());
		assertEquals("Chocolate Ice Cream", result.get(0).getItemName());
		assertEquals("Choco Lava", result.get(1).getItemName());
		assertEquals("Mango Ice Cream", result.get(2).getItemName());
		verify(menuItemsRepository).findAll();
	}
 
	@Test
	public void testAddMenuItems() throws DuplicateItemIDException {
		MenuItems items = new MenuItems(58, "Chciken Biryani","Birayni with lot of spices",13.67);
		when(menuItemsRepository.findById(58)).thenReturn(Optional.empty());
		when(menuItemsRepository.saveAndFlush(items)).thenReturn(items);
 
		int id = menuItemsService.addMenuItems(items);
 
		assertEquals(58, id);
		verify(menuItemsRepository).findById(58);
		verify(menuItemsRepository).saveAndFlush(items);
	}
 
	@Test
	public void testAddMenuItemsWithDuplicateItemID() {
		// Given
		MenuItems existingItem = new MenuItems(54, "Chocolate Ice Cream", "Icecream filled with toppings",7.99);
		when(menuItemsRepository.findById(54)).thenReturn(Optional.of(existingItem));
 
		// Then
		assertThrows(DuplicateItemIDException.class, () -> {
			// When
			menuItemsService.addMenuItems(existingItem);
		});
 
		// Verify that saveAndFlush method is not called
		verify(menuItemsRepository, never()).saveAndFlush(existingItem);
	}
 
//	@Test
//	public void testDeleteCustomerById() {
//		// Given
//		int customerId = 101;
// 
//		// When
//		customerService.deleteCustomerByID(customerId);
// 
//		// Then
//		verify(customerRepository).deleteById(customerId);
//	}
}
