package com.example.tripbros.domain;

import com.example.tripbros.domain.enumerate.Gender;
import com.example.tripbros.domain.enumerate.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue
	private Long id;
	private String email;
	private String nickname;
	private int age;

	@Enumerated(EnumType.STRING)
	private Gender gender;
	private String travelStyle;

	@Column(columnDefinition = "TEXT") //Nullable
	private String profileImage;

	@Enumerated(EnumType.STRING)
	private Role role;

	@Builder
	public User(String email, String nickname, int age, Gender gender, String travelStyle, String profileImage,
		Role role) {
		this.email = email;
		this.nickname = nickname;
		this.age = age;
		this.gender = gender;
		this.travelStyle = travelStyle;
		this.profileImage = profileImage;
		this.role = role;
	}

	public User() {
	}
}
