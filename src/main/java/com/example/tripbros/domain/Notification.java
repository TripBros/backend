package com.example.tripbros.domain;

import com.example.tripbros.domain.enumerate.Soulmate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Notification {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private Soulmate soulmate;

	@Builder
	public Notification(User user, Soulmate soulmate) {
		this.user = user;
		this.soulmate = soulmate;
	}

	public Notification() {
	}
}
