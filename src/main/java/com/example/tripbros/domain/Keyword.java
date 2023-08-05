package com.example.tripbros.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Keyword {
	@Id
	@GeneratedValue
	private Long id;

	@Column(columnDefinition = "TEXT")
	private String message;

	@Builder
	public Keyword(String message) {
		this.message = message;
	}

	public Keyword() {
	}
}
