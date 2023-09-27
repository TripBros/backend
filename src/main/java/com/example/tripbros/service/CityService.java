package com.example.tripbros.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.tripbros.domain.City;
import com.example.tripbros.repository.CityRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CityService {
	private final CityRepository cityRepository;

	public Optional<City> getCity(String name){
		return cityRepository.findByName(name);

	}
}
