package com.example.tripbros.domain;

import com.example.tripbros.domain.enumerate.Gender;
import com.example.tripbros.domain.enumerate.Role;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.Locale;

@Entity
@Getter
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Builder
	public User(String email, int ageRange, Gender gender) {
		this.email = email;
		this.age = ageRange;
		this.gender = gender;
	}

	public User() {
	}

}
