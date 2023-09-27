package com.example.tripbros.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.example.tripbros.domain.City;
import com.example.tripbros.dto.RestaurantReturnDTO;
import com.example.tripbros.repository.RestaurantRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantService {
	private final RestaurantRepository restaurantRepository;
	private final CityService cityService;
	public List<RestaurantReturnDTO> getRestaurants(String cityName, Integer width, Integer height){
		City city = cityService.getCity(cityName)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 도시입니다."));
		List<RestaurantReturnDTO> result = new ArrayList<>();
		restaurantRepository.findAllByCity(city).forEach(restaurant -> {
			String link = restaurant.getImg();
			restaurant.setImg(setImageScale(link, width, height));
			result.add(new RestaurantReturnDTO(restaurant));
		});
		return result;
	}

	public String setImageScale(String imageUrl, long width, long height){
		String regex = "w(\\d+)-h(\\d+)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(imageUrl);

		if (matcher.find()) {
			String oldWidth = matcher.group(1);
			String oldHeight = matcher.group(2);

			return imageUrl.replace("w" + oldWidth + "-h" + oldHeight, "w" + width + "-h" + height);
		} else {
			// 패턴이 일치하지 않으면 원래 URL 반환
			return imageUrl;
		}


	}
}
