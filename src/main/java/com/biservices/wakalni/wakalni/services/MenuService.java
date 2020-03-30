package com.biservices.wakalni.wakalni.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biservices.wakalni.wakalni.persistence.entities.Comment;
import com.biservices.wakalni.wakalni.persistence.entities.Menu;
import com.biservices.wakalni.wakalni.persistence.entities.MenuCategory;
import com.biservices.wakalni.wakalni.persistence.entities.Restaurant;
import com.biservices.wakalni.wakalni.persistence.entities.user.PlatformUser;
import com.biservices.wakalni.wakalni.persistence.repositories.MenuCategoryRepository;
import com.biservices.wakalni.wakalni.persistence.repositories.MenuRepository;
import com.biservices.wakalni.wakalni.persistence.repositories.RestaurantRepository;

@Service
public class MenuService {

	@Autowired
	private MenuRepository menuRepo;

	@Autowired
	private MenuCategoryRepository menuCategoryRepo;

	@Autowired
	private RestaurantRepository restoRepo;
	
	@Autowired
	private CustomUserDetailsService userService;

	public Menu getMenuById(Long id) {
		return menuRepo.findOne(id);
	}

	public List<Menu> findAllMenusForRestaurant(Long restaurantId) {
		Restaurant resto = restoRepo.findOne(restaurantId);
		return menuRepo.findByRestaurant(resto);
	}

	public List<Menu> findAllMenusForMenuCategory(Long menuCategoryId) {
		MenuCategory menuCategory = menuCategoryRepo.findOne(menuCategoryId);
		return menuRepo.findByMenuCategory(menuCategory);
	}

	public Menu saveNewMenu(Menu menu, Long restaurantId, Long menuCategoryId) {
		MenuCategory menuCategory = menuCategoryRepo.findOne(menuCategoryId);
		Restaurant resto = restoRepo.findOne(restaurantId);
		menu.setMenuCategory(menuCategory);
		menu.setRestaurant(resto);
		return menuRepo.save(menu);

	}

	public List<Menu> getMenusForRestaurant(Long restaurantId) {
		Restaurant resto = restoRepo.findOne(restaurantId);
		if (resto != null) {
			List<Menu> menus = menuRepo.findByRestaurant(resto);
			return menus;
		}
		return null;
	}

	public void deleteMenu(Long menuId) {
		Menu menu = menuRepo.findOne(menuId);
		if (menu != null) {
			menuRepo.delete(menu);
		}
	}

	public Menu editMenu(Long menuId) {
		Menu menu = menuRepo.findOne(menuId);
		if (menu != null) {
			return menuRepo.save(menu);
		}
		return null;
	}

	public List<Menu> getTop10Menus() {
		// TODO Auto-generated method stub
		return menuRepo.findTop10ByOrderByLikeNumberDesc();
	}
	
	public List<Menu> getFlop10Menus() {
		// TODO Auto-generated method stub
		return menuRepo.findTop10ByOrderByLikeNumberDesc();
	}

	public Menu addCommentForMenu(Comment comment, Long menuId, Long userId) {
		Menu menu = menuRepo.findOne(menuId);
		if (menu != null) {
			if(menu.getComments() == null) {
				menu.setComments(new ArrayList<Comment>());
			}
			PlatformUser user = userService.getUserById(userId);
			comment.setUser(user);
			menu.getComments().add(comment);
			return menuRepo.save(menu);
		}
		return null;
	}
}
