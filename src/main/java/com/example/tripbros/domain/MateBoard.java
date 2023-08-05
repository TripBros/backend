package com.example.tripbros.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.example.tripbros.domain.enumerate.Gender;
import com.example.tripbros.domain.enumerate.Status;
import com.example.tripbros.domain.enumerate.TripPurpose;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;

@Entity
@Getter
public class MateBoard {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private String title;

	@OneToMany(mappedBy = "mateBoard")
	private List<TripDate> tripDateList;
	private TripPurpose tripPurpose;
	private LocalDate startDate;
	private LocalDate endDate;
	private int targetMatchAge;
	private Gender targetMatchGender;
	private int budget;

	@Column(columnDefinition = "TEXT")
	private String content;

	private Status status;
	private LocalDateTime regDateTime;

	public MateBoard(User user, String title, List<TripDate> tripDateList, TripPurpose tripPurpose, LocalDate startDate,
		LocalDate endDate, int targetMatchAge, Gender targetMatchGender, int budget, String content, Status status,
		LocalDateTime regDateTime) {
		this.user = user;
		this.title = title;
		this.tripDateList = tripDateList;
		this.tripPurpose = tripPurpose;
		this.startDate = startDate;
		this.endDate = endDate;
		this.targetMatchAge = targetMatchAge;
		this.targetMatchGender = targetMatchGender;
		this.budget = budget;
		this.content = content;
		this.status = status;
		this.regDateTime = regDateTime;
	}

	public MateBoard() {
	}
}
