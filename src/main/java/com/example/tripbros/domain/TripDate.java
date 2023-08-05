package com.example.tripbros.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class TripDate {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "mate_board_id")
	private MateBoard mateBoard;

	@OneToOne
	@JoinColumn(name = "city_id")
	private City city;

	private LocalDate startDate;
	private LocalDate endDate;

	@Builder
	public TripDate(MateBoard mateBoard, City city, LocalDate startDate, LocalDate endDate) {
		this.mateBoard = mateBoard;
		this.city = city;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public TripDate() {
	}
}
