package com.biservices.wakalni.wakalni.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biservices.wakalni.wakalni.persistence.entities.MenuCategory;
import com.biservices.wakalni.wakalni.persistence.entities.Restaurant;
@Repository
public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long> {
	public List<MenuCategory> findByRestaurant(Restaurant resto);

}
