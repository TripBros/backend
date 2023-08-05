package com.example.tripbros.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class RefreshToken {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	private String refreshToken;

	@Builder
	public RefreshToken(User user, String refreshToken) {
		this.user = user;
		this.refreshToken = refreshToken;
	}

	public RefreshToken() {
	}
}
