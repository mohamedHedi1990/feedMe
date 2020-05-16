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

import com.biservices.wakalni.wakalni.model.dto.MenuDto;
import com.biservices.wakalni.wakalni.persistence.entities.Comment;
import com.biservices.wakalni.wakalni.persistence.entities.Menu;
import com.biservices.wakalni.wakalni.services.DBFileStorageService;
import com.biservices.wakalni.wakalni.services.MenuService;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@Autowired
	private DBFileStorageService DBFileStorageService;
	

	@CrossOrigin
	@GetMapping("/{menuId}")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public MenuDto getMenuById(@PathVariable("menuId") Long menuId) {

		return menuService.getMenuDtoById(menuId);
	}
	
	
	/*@CrossOrigin
	@PostMapping("/{restaurantId}/{menuCategoryId}")
	public Menu saveNewMenu(@RequestBody Menu menu, @RequestParam("file") MultipartFile file, @PathVariable("restaurantId") Long restaurantId,
			@PathVariable("menuCategoryId") Long menuCategoryId) {
		try {
			String fileName = DBFileStorageService.storeFile(file);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/wakalni/"+menu.getName()+"/")
						.path(fileName).toUriString();
			menu.setPictureUrl(fileDownloadUri);
			return menuService.saveNewMenu(menu, restaurantId, menuCategoryId);
		} catch (FileStorageException e) {
			System.out.println("Internal error was occured when trying to save menu picture file! \n");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Internal error was occured when trying to save the informations of this new menu \n");
			e.printStackTrace();
		}
		return null;
	}*/
	
	@CrossOrigin
	@PostMapping("/{restaurantId}/{menuCategoryId}")
	public Menu saveNewMenu(@RequestBody Menu menu, @PathVariable("restaurantId") Long restaurantId,
			@PathVariable("menuCategoryId") Long menuCategoryId) {
		try {
			return menuService.saveNewMenu(menu, restaurantId, menuCategoryId);
		} catch (Exception e) {
			System.out.println("Internal error was occured when trying to save the informations of this new menu \n");
			e.printStackTrace();
		}
		return null;
	}
	
	@CrossOrigin
	@GetMapping("for_restaurant/{restaurantId}")
	public List<MenuDto> getMenusForRestaurant(@PathVariable("restaurantId") Long restaurantId) {
		try {
			return menuService.findAllMenusForRestaurant(restaurantId);
		} catch (Exception e) {
			System.out.println("Internal error was occured when trying to save the informations of this new menu \n");
			e.printStackTrace();
		}
		return null;
	}
	
	@CrossOrigin
	@GetMapping("for_restaurant/{restaurantId}/{menuCategoryId}")
	public List<MenuDto> getMenusForRestaurantAndCategory(@PathVariable("restaurantId") Long restaurantId,
			@PathVariable("menuCategoryId") Long menuCategoryId) {
		try {
			return menuService.findAllMenusForRestaurantAndCategory(restaurantId, menuCategoryId);
		} catch (Exception e) {
			System.out.println("Internal error was occured when trying to save the informations of this new menu \n");
			e.printStackTrace();
		}
		return null;
	}
	
	@CrossOrigin
	@DeleteMapping("/{menuId}")
	public void deleteMenu(@PathVariable("menuId") Long menuId) {
		try {
			menuService.deleteMenu(menuId);
		} catch (Exception e) {
			System.out.println("Internal error was occured when trying to save the informations of this new menu \n");
			e.printStackTrace();
		}
	}
	
	@CrossOrigin
	@PutMapping("/{menuId}")
	public Menu editMenu(@PathVariable("menuId") Long menuId) {
		try {
			return menuService.editMenu(menuId);
		} catch (Exception e) {
			System.out.println("Internal error was occured when trying to save the informations of this new menu \n");
			e.printStackTrace();
		}
		return null;
	}
	
	@CrossOrigin
	@GetMapping("top")
	public List<Menu> getTop10Menus() {
		try {
			return menuService.getTop10Menus();
		} catch (Exception e) {
			System.out.println("Internal error was occured when trying to get the top 10 menus \n");
			e.printStackTrace();
		}
		return null;
	}
	
	@CrossOrigin
	@GetMapping("flop")
	public List<Menu> getFlop10Menus() {
		try {
			return menuService.getFlop10Menus();
		} catch (Exception e) {
			System.out.println("Internal error was occured when trying to get the flop 10 menus \n");
			e.printStackTrace();
		}
		return null;
	}
	
	@CrossOrigin
	@PutMapping("/comment/{menuId}/{userId}")
	public Menu addComment(@RequestBody Comment comment, @PathVariable("menuId") Long menuId, @PathVariable("userId") Long userId) {
		try {
			return menuService.addCommentForMenu(comment, menuId, userId);
		} catch (Exception e) {
			System.out.println("Internal error was occured when trying to add comment \n");
			e.printStackTrace();
		}
		return null;
	}
	
	
}
