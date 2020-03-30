package com.biservices.wakalni.wakalni.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biservices.wakalni.wakalni.persistence.entities.Menu;
import com.biservices.wakalni.wakalni.persistence.entities.MenuCategory;
import com.biservices.wakalni.wakalni.persistence.entities.Restaurant;
import com.biservices.wakalni.wakalni.persistence.repositories.MenuCategoryRepository;
import com.biservices.wakalni.wakalni.persistence.repositories.MenuRepository;
import com.biservices.wakalni.wakalni.persistence.repositories.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository restoRepo;

	@Autowired
	private MenuRepository menuRepo;

	@Autowired
	private MenuCategoryRepository menuCategoryRepo;

	public Restaurant saveRestaurant(Restaurant resto) {
		return restoRepo.save(resto);
	}

	public List<Restaurant> filterByField(String field) {
		List<Restaurant> restos = new ArrayList<Restaurant>();
		restos.addAll(restoRepo.findByAddressContaining(field));
		restos.addAll(restoRepo.findByCity(field));
		restos.addAll(restoRepo.findByNameContaining(field));
		restos.addAll(restoRepo.findByPostalCode(field));
		
		Set<Restaurant> restosSet = new HashSet<>(restos);
		List<Restaurant> targetRestos = new ArrayList<>(restosSet);

		return targetRestos;
	}

	public Restaurant updateStarsAverage(Long restaurantId, Integer starsNumber) {
		Restaurant resto = restoRepo.findOne(restaurantId);
		double previousStrasAverage = resto.getStarsAverage();
		resto.setStarsAverage((previousStrasAverage + starsNumber) / 2);
		return restoRepo.save(resto);

	}

	public List<MenuCategory> getRestaurantMenuCatgeories(Long restaurantId) {
		Restaurant resto = restoRepo.findOne(restaurantId);
		return menuCategoryRepo.findByRestaurant(resto);
	}

	public Restaurant updateRestarantPicture(String fileDownloadUri, Long restaurantId) {
		if (restaurantId != null) {
			Restaurant resto = restoRepo.findOne(restaurantId);
			resto.setPictureUrl(fileDownloadUri);
			return restoRepo.save(resto);
		}
		return null;
	}

	public Menu updateMenuPicture(String fileDownloadUri, Long menuId) {
		if (menuId != null) {
			Menu menu = menuRepo.findOne(menuId);
			menu.setPictureUrl(fileDownloadUri);
			return menuRepo.save(menu);
		}
		return null;
	}

	public List<Restaurant> getAllRestaurants() {
		return restoRepo.findAll();
	}

	public void deleteRestaurant(Long restaurantId) {
		Restaurant resto = restoRepo.findOne(restaurantId);
		if (resto != null) {
			restoRepo.delete(resto);
		}
	}

	public Restaurant editRestaurant(Long restaurantId) {
		Restaurant resto = restoRepo.findOne(restaurantId);
		if (resto != null) {
			return restoRepo.save(resto);
		}
		return null;
	}

	public Restaurant getRestaurantById(Long restaurantId) {
		return restoRepo.findOne(restaurantId);
	}
	
	public List<Restaurant> getTopRestaurants() {
		return restoRepo.findTop10ByOrderByStarsAverageDesc();
	}

	public Restaurant updateAverageStars(Long restaurantId, Integer scoreNumber) {
		Restaurant resto =restoRepo.findOne(restaurantId);
		resto.setStarsAverage((resto.getStarsAverage() + scoreNumber)/2);
		return restoRepo.save(resto);
	}
}
