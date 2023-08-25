package com.example.tripbros.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class RefreshToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id", nullable = false, unique = true)
	private Long userId;

	@Column(name="refresh_token", nullable = false)
	private String refreshToken;

	@Builder
	public RefreshToken(Long userId, String refreshToken) {
		this.userId = userId;
		this.refreshToken = refreshToken;
	}

	public RefreshToken() {
	}

	public RefreshToken update(String newRefreshToken) {
		this.refreshToken = newRefreshToken;
		return this;
	}
}
