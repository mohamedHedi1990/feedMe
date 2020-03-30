package com.biservices.wakalni.wakalni.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biservices.wakalni.wakalni.persistence.entities.MenuCategory;
import com.biservices.wakalni.wakalni.persistence.entities.Restaurant;
import com.biservices.wakalni.wakalni.persistence.repositories.MenuCategoryRepository;
import com.biservices.wakalni.wakalni.persistence.repositories.RestaurantRepository;

@Service
public class MenuCategoryService {
	
	@Autowired
	private MenuCategoryRepository menuCategoryRepo;
	
	@Autowired
	private RestaurantRepository restoRepo;
	
	/* public MenuCategory saveNewMenuCategory(MenuCategory menuCategory) {
		return menuCategoryRepo.save(menuCategory);
	} */

	public MenuCategory saveNewMenuCategory(MenuCategory menuCategory, Long restaurantId) {
		Restaurant resto = restoRepo.findOne(restaurantId);
		menuCategory.setRestaurant(resto);
		return menuCategoryRepo.save(menuCategory);
	}

	public List<MenuCategory> getAllMenuCategoriesForRestaurant(Long restaurantId) {
		Restaurant resto = restoRepo.findOne(restaurantId);
		return menuCategoryRepo.findByRestaurant(resto);
	}

	public void deleteMenuCategory(Long menuCategoryId) {
		MenuCategory menuCategory = menuCategoryRepo.findOne(menuCategoryId);
		if(menuCategory != null)
		menuCategoryRepo.delete(menuCategory);
		
	}
	
	public MenuCategory editMenuCategory(Long menuCategoryId) {
		MenuCategory menuCategory = menuCategoryRepo.findOne(menuCategoryId);
		if(menuCategory != null)
		menuCategoryRepo.save(menuCategory);
		
		return null;
		
	}

	public MenuCategory getMenuCategory(Long menuCategoryId) {
		MenuCategory menuCategory = menuCategoryRepo.findOne(menuCategoryId);
		return menuCategory;
	}

}
