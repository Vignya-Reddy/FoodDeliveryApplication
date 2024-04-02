package com.fooddelivery.menuitems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.menuitems.service.MenuItemsService;
import com.fooddelivery.model.MenuItems;

@RestController
@RequestMapping(value = "/api/restaurants/{restaurantId}")
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
    ResponseEntity<MenuItems> addMenuItems(MenuItems items){
        int menuId = menuService.addMenuItems(items);
        return ResponseEntity.ok(items);
    }
   

}
 