package com.example.tripbros.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RestaurantForm {
	@Schema(description = "대륙",example = "Asia")
	@NotBlank
	public String continent;

	@Schema(description = "국가",example = "Japan")
	@NotBlank
	public String country;

	@Schema(description = "도시",example = "Fukuoka")
	@NotBlank
	public String city;


	//width, height는 대표 사진 이미지의 크기를 지정함.
	@Schema(description = "이미지의 width",example = "500")
	public long width;
	@Schema(description = "이미지의 height",example = "500")
	public long height;


}
