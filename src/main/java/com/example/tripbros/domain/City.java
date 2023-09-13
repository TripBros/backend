package com.example.tripbros.domain;

import com.example.tripbros.domain.enumerate.Continent;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class City {
	@Id
	@GeneratedValue
	private Long id;
	private String continent;
	private String country;
	private String city;

	@Builder
	public City(String  continent, String country, String city) {
		this.continent = continent;
		this.country = country;
		this.city = city;
	}

	public City() {
	}
}
