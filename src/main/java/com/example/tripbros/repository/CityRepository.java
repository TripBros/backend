package com.example.tripbros.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tripbros.domain.City;
import com.example.tripbros.domain.enumerate.Continent;

public interface CityRepository extends JpaRepository<City, Long> {
	Optional<City> findByContinentAndCountryAndCity(String continent, String country, String city );
}
