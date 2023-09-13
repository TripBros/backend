package com.example.tripbros.dto;

import com.example.tripbros.domain.Restaurant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RestaurantReturnDTO {
	@Schema(description = "메뉴 이름")
	private String name;
	@Schema(description = "가게 주소")
	private String address;
	@Schema(description = "가게 별점")
	private float rating;
	@Schema(description = "가게 리뷰 (한줄)")
	private String review;
	@Schema(description = "가게 구글맵 링크")
	private String link;
	@Schema(description = "대표 이미지 링크")
	private String img;

	public RestaurantReturnDTO(Restaurant restaurant){
		this.name = restaurant.getName();
		this.address = restaurant.getAddress();
		this.rating = restaurant.getRating();
		this.review = restaurant.getReview();
		this.link = restaurant.getLink();
		this.img = restaurant.getImg();
	}
}
