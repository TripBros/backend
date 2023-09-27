package com.example.tripbros.controller;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tripbros.dto.RestaurantReturnDTO;
import com.example.tripbros.service.RestaurantService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurant")
@Slf4j
@Tag(name = "Restaurant", description = "맛집 API")
public class RestaurantController {
	private final RestaurantService service;

	@GetMapping
	@Operation(summary = "맛집 조회")
	public List<RestaurantReturnDTO> getRestaurant(@RequestParam @Parameter(description = "도시 이름") String city,
		@RequestParam(defaultValue = "128") @Parameter(description = "이미지 width") Integer width,
		@RequestParam(defaultValue = "128") @Parameter(description = "이미지 height") Integer height){

		return service.getRestaurants(city, width, height);
	}

}
