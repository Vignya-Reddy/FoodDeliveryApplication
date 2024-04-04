package com.fooddelivery.menuitems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.exception.DuplicateItemIDException;
import com.fooddelivery.exception.InvalidItemIDException;
import com.fooddelivery.exception.SuccessResponse;
import com.fooddelivery.menuitems.service.MenuItemsService;
import com.fooddelivery.model.MenuItems;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping(value = "/api/restaurants/items")
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
	ResponseEntity<Object> addMenuItems(@Valid  @RequestBody MenuItems items) throws DuplicateItemIDException, InvalidItemIDException{
	   if(items.getItemId()<=0) {
			throw new InvalidItemIDException("Item ID is invalid");
		}
		int menuId=menuService.addMenuItems(items);
		if(menuId==0) {
			throw new DuplicateItemIDException("Item with ID "+items.getItemId()+" already Exists");
		}
		System.out.println("Item ID in controller is "+menuId);	
		SuccessResponse successResponse = new SuccessResponse("Restaurants are fetched successfully", "200");
        String jsonResponse = "{\"message\": \"" + successResponse.getMessage() + "\", \"code\": \"" + successResponse.getCode() + "\"}";
        return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
	}

   

}
 