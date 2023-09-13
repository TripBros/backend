package com.example.tripbros.controller;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tripbros.domain.Restaurant;
import com.example.tripbros.dto.RestaurantReturnDTO;
import com.example.tripbros.service.RestaurantService;
import com.example.tripbros.dto.RestaurantForm;

import io.swagger.v3.oas.annotations.Operation;
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
	public List<RestaurantReturnDTO> getRestaurant(@Valid @RequestBody RestaurantForm form, BindingResult bindingResult){

		if (bindingResult.hasErrors()){
			throw new IllegalArgumentException("입력이 잘못되었습니다." + bindingResult.getAllErrors());
		}
		return service.getRestaurants(form);
	}

}
