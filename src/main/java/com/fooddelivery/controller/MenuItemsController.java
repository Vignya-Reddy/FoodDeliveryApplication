package com.fooddelivery.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.dto.MenuItemsDTO;
import com.fooddelivery.entity.MenuItems;
import com.fooddelivery.entity.Restaurant;
import com.fooddelivery.exception.CustomException;
import com.fooddelivery.exception.DuplicateItemIDException;
import com.fooddelivery.exception.InvalidItemIDException;
import com.fooddelivery.exception.ItemNotFoundException;
import com.fooddelivery.exception.RestaurantNotFoundException;
import com.fooddelivery.service.MenuItemsService;
import com.fooddelivery.util.SuccessResponse;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping(value = "/api/menuitems")
public class MenuItemsController {
    @Autowired
    MenuItemsService menuService;
    
    
    @GetMapping(produces = "application/json")
    List<MenuItemsDTO> showMenuItems() throws CustomException {
        List<MenuItems> menuList = menuService.showMenuItems();
        List<MenuItemsDTO> dtoList = new ArrayList<>();
        for (MenuItems menuItem : menuList) {
            dtoList.add(MenuItemsDTO.fromMenuItems(menuItem));
        }
        return dtoList;
    }
   
    
    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<Object> addMenuItems(@Valid @RequestBody MenuItemsDTO menuItemDTO) throws CustomException {
        MenuItems menuItem = new MenuItems();
        menuItem.setItemId(menuItemDTO.getItemId());
        menuItem.setItemName(menuItemDTO.getItemName());
        menuItem.setItemDescription(menuItemDTO.getItemDescription());
        menuItem.setItemPrice(menuItemDTO.getItemPrice());
        
        int menuItemId = menuService.addMenuItems(menuItem);
        
        SuccessResponse successResponse = new SuccessResponse("Menu item is added successfully", "200");
        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";
        
        return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
    }
    
    
    @PutMapping("/{itemId}")
    ResponseEntity<Object> updateMenuItem(@Valid @RequestBody MenuItemsDTO menuItemDTO) throws CustomException {
        MenuItems menuItem = new MenuItems();
        menuItem.setItemId(menuItemDTO.getItemId());
        menuItem.setItemName(menuItemDTO.getItemName());
        menuItem.setItemDescription(menuItemDTO.getItemDescription());
        menuItem.setItemPrice(menuItemDTO.getItemPrice());
        
        MenuItems updatedMenuItem = menuService.updateMenuItem(menuItem);
        
        MenuItemsDTO updatedMenuItemDTO = MenuItemsDTO.fromMenuItems(updatedMenuItem);
        
        SuccessResponse successResponse = new SuccessResponse("Menu item is updated successfully", "200");
        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";
        
        return ResponseEntity.ok(updatedMenuItemDTO);
    }
    
    
    
	@DeleteMapping("/{itemId}")
	ResponseEntity<Object> deleteMenuItem(@PathVariable("itemId") Integer itemId ) throws CustomException {
		menuService.deleteMenuItemByID(itemId);
		SuccessResponse successResponse = new SuccessResponse("Items are deleted successfully", "200");
        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";
        return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
	}
	
	@GetMapping("/{restaurantId}/menu")
	public ResponseEntity<Object> findMenuItemsByRestaurantId(@PathVariable int restaurantId) throws CustomException {
	    List<MenuItems> menuItem = menuService.findMenuItemsByRestaurantId(restaurantId);
	    
//	    if (menuItem.isEmpty()) {
//	       throw new CustomException("No items are found");
//	    }
	    
	    List<MenuItemsDTO> menuItemsDTO = new ArrayList<>();
	    for (MenuItems item : menuItem) {
	        menuItemsDTO.add(MenuItemsDTO.fromMenuItems(item));
	    }
	    
	    SuccessResponse successResponse = new SuccessResponse("Menu items retrieved successfully", "200");
	    return ResponseEntity.ok().body(Map.of("menuItems", menuItemsDTO, "message", successResponse.getMessage(), "code", successResponse.getCode()));
	}

	

}
 