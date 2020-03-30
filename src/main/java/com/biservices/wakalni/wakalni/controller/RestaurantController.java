package com.biservices.wakalni.wakalni.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biservices.wakalni.wakalni.persistence.entities.Comment;
import com.biservices.wakalni.wakalni.persistence.entities.Menu;
import com.biservices.wakalni.wakalni.persistence.entities.MenuCategory;
import com.biservices.wakalni.wakalni.persistence.entities.Restaurant;
import com.biservices.wakalni.wakalni.services.RestaurantService;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantService restoSevcie;
	
	

	@CrossOrigin
	@PostMapping()
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Restaurant saveNewRestaurant(@RequestBody Restaurant restaurant) {

		return restoSevcie.saveRestaurant(restaurant);
	}
	
	@CrossOrigin
	@GetMapping("/{filterField}")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Restaurant> getRestaurantByField(@PathVariable("filterField") String filterField) {

		return restoSevcie.filterByField(filterField);
	}
	
	@CrossOrigin
	@GetMapping("restaurantId/{restaurantId}")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Restaurant getRestaurantById(@PathVariable("restaurantId") Long restaurantId) {

		return restoSevcie.getRestaurantById(restaurantId);
	}
	
	@CrossOrigin
	@GetMapping()
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Restaurant> getRestaurants() {

		return restoSevcie.getAllRestaurants();
	}
	
	@CrossOrigin
	@GetMapping("/{restaurantId}/menu_categories")
	//@PreAuthorize("hasRole('ROLE_USER')")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<MenuCategory> getMenuCategories(@PathVariable("restaurantId") Long restaurantId) {

		return restoSevcie.getRestaurantMenuCatgeories(restaurantId);
	}
	
	@CrossOrigin
	@GetMapping("top")
	//@PreAuthorize("hasRole('ROLE_USER')")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Restaurant> getTopRestaurants() {

		return restoSevcie.getAllRestaurants();
	}
	
	@CrossOrigin
	@DeleteMapping("/{restaurantId}")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteRestaurant(@PathVariable("restaurantId") Long restaurantId) {
		 restoSevcie.deleteRestaurant(restaurantId);
	}
	
	@CrossOrigin
	@PutMapping("/{restaurantId}")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Restaurant editRestaurant(@PathVariable("restaurantId") Long restaurantId) {
		 return restoSevcie.editRestaurant(restaurantId);
	}
	
	@CrossOrigin
	@PutMapping("/score/{restaurantId}/{scoreNumber}")
	public Restaurant addAScore(@PathVariable("restaurantId") Long restaurantId, @PathVariable("scoreNumber") Integer scoreNumber) {
		try {
			return restoSevcie.updateAverageStars(restaurantId, scoreNumber);
		} catch (Exception e) {
			System.out.println("Internal error was occured when trying to add this score \n");
			e.printStackTrace();
		}
		return null;
	}
	
}
