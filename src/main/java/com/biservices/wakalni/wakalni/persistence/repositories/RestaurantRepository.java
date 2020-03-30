package com.biservices.wakalni.wakalni.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biservices.wakalni.wakalni.persistence.entities.Menu;
import com.biservices.wakalni.wakalni.persistence.entities.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	public List<Restaurant> findByCity(String city);
	public List<Restaurant> findByPostalCode(String postalCode);
	public List<Restaurant> findByAddressContaining(String address);
	public List<Restaurant> findByNameContaining(String name);
	public List<Restaurant> findTop10ByOrderByStarsAverageDesc();
}
