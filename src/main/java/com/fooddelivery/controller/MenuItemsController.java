package com.fooddelivery.controller;

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

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping(value = "/api/menuitems")
public class MenuItemsController {
    @Autowired
    MenuItemsService menuService;
    
    
    @GetMapping(produces = "application/json")
    List<MenuItems> showMenuItems(){
        System.out.println("MenuItems Controller");
        List<MenuItems> menuList=menuService.showMenuItems();
        return menuList;
    }
   
    
    @PostMapping(consumes = "application/json",produces = "application/json")
	ResponseEntity<Object> addMenuItems(@Valid  @RequestBody MenuItems items) throws CustomException{
//	   if(items.getItemId()<=0) {
//			throw new CustomException("Item ID is invalid");
//		}
		int menuId=menuService.addMenuItems(items);
//		if(menuId==0) {
//			throw new CustomException("Item with ID "+items.getItemId()+" already Exists");
//		}
		System.out.println("Item ID in controller is "+menuId);	
		SuccessResponse successResponse = new SuccessResponse("Items are added successfully", "200");
        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";
        return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
	}
    
    
    @PutMapping("/{itemId}")
    ResponseEntity<Object> updateMenuItem(@Valid @RequestBody MenuItems items) throws CustomException{
    	MenuItems menu = menuService.updateMenuItem(items);
		if(items == null) {
			throw new CustomException("MenuItem not present");
		}
		System.out.println("Item ID in controller");		
		
		SuccessResponse successResponse = new SuccessResponse("Items are updated successfully", "200");
        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";
        return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
	}
    
    
    
	@DeleteMapping("/{itemId}")
	ResponseEntity<Object> deleteMenuItem(@PathVariable("itemId") Integer itemId ) throws CustomException {
		menuService.deleteMenuItemByID(itemId);
		SuccessResponse successResponse = new SuccessResponse("Items are deleted successfully", "200");
        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";
        return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
	}
	
	
	
	
	

    
   

}
 