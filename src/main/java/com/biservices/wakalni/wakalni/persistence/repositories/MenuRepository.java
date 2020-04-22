package com.biservices.wakalni.wakalni.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biservices.wakalni.wakalni.persistence.entities.Menu;
import com.biservices.wakalni.wakalni.persistence.entities.MenuCategory;
import com.biservices.wakalni.wakalni.persistence.entities.Restaurant;
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
	List<Menu> findByRestaurant (Restaurant resto);
	List<Menu> findByMenuCategory (MenuCategory menuCategory);
	List<Menu> findByRestaurantAndMenuCategory (Restaurant resto, MenuCategory menuCategory);
	List<Menu> findTop10ByOrderByLikeNumberDesc();
	List<Menu> findTop10ByOrderByDontLikeNumberDesc();
	
}
