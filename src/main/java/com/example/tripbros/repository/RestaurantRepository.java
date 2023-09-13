package com.example.tripbros.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tripbros.domain.City;
import com.example.tripbros.domain.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	List<Restaurant> findAllByCity(City city);
}
