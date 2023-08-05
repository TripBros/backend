package com.example.tripbros.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Restaurant {
	@Id
	@GeneratedValue
	private Long id;

	@OneToOne
	@JoinColumn(name = "city_id")
	private City city;

	private String name;
	@Column(columnDefinition = "TEXT")
	private String address;
	private float rating;
	@Column(columnDefinition = "TEXT")
	private String review;
	@Column(columnDefinition = "TEXT")
	private String link;

	@Builder
	public Restaurant(City city, String name, String address, float rating, String review, String link) {
		this.city = city;
		this.name = name;
		this.address = address;
		this.rating = rating;
		this.review = review;
		this.link = link;
	}

	public Restaurant() {
	}
}
