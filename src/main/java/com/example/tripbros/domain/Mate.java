package com.example.tripbros.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Mate {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "mate_board_id")
	private MateBoard mateBoard;

	private Long mateId; //TODO: 유효성 확인

	@Builder
	public Mate(MateBoard mateBoard, Long mateId) {
		this.mateBoard = mateBoard;
		this.mateId = mateId;
	}

	public Mate() {
	}
}
