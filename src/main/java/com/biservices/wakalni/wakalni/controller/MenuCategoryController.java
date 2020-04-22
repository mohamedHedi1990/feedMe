package com.biservices.wakalni.wakalni.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biservices.wakalni.wakalni.model.dto.MenuCategoryDto;
import com.biservices.wakalni.wakalni.persistence.entities.MenuCategory;
import com.biservices.wakalni.wakalni.services.DBFileStorageService;
import com.biservices.wakalni.wakalni.services.MenuCategoryService;
import com.biservices.wakalni.wakalni.services.RestaurantService;

@RestController
@RequestMapping("/api/menu_category")
public class MenuCategoryController {

	@Autowired
	private MenuCategoryService menuCategoryService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private DBFileStorageService DBFileStorageService;
	

	@CrossOrigin
	@PostMapping("/{restaurantId}")
	public MenuCategory saveNewMenu(@RequestBody MenuCategory menuCategory, @PathVariable("restaurantId") Long restaurantId) {
		try {
			return menuCategoryService.saveNewMenuCategory(menuCategory, restaurantId);
		}  catch (Exception e) {
			System.out.println("Internal error was occured when trying to save the informations of this new menu category \n");
			e.printStackTrace();
		}
		return null;
	}
	
	@CrossOrigin
	@GetMapping("/{restaurantId}")
	public List<MenuCategoryDto> getAllMenuCategoriesForRestaurant(@PathVariable("restaurantId") Long restaurantId) {
		try {
			return menuCategoryService.getAllMenuCategoriesForRestaurant(restaurantId);
		}  catch (Exception e) {
			System.out.println("Internal error was occured when trying the new menu categories for this restaurant \n");
			e.printStackTrace();
		}
		return null;
	}
	
	@CrossOrigin
	@GetMapping("menuCategoryId/{menuCategoryId}")
	public MenuCategory getMenuCategory(@PathVariable("menuCategoryId") Long menuCategoryId) {
		try {
			return menuCategoryService.getMenuCategory(menuCategoryId);
		}  catch (Exception e) {
			System.out.println("Internal error was occured when trying to get this menu category \n");
			e.printStackTrace();
		}
		return null;
	}
	
	@CrossOrigin
	@DeleteMapping("{menuCategoryId}")
	public void deleteMenuCategory(@PathVariable("menuCategoryId") Long menuCategoryId) {
		try {
			menuCategoryService.deleteMenuCategory(menuCategoryId);
		}  catch (Exception e) {
			System.out.println("Internal error was occured when trying to delete this menu category \n");
			e.printStackTrace();
		}
	}
	
	@CrossOrigin
	@PutMapping("{menuCategoryId}")
	public MenuCategory editMenuCategory(@PathVariable("menuCategoryId") Long menuCategoryId) {
		try {
			return menuCategoryService.editMenuCategory(menuCategoryId);
		}  catch (Exception e) {
			System.out.println("Internal error was occured when trying to delete this menu category \n");
			e.printStackTrace();
		}
		return null;
	}
	
}
