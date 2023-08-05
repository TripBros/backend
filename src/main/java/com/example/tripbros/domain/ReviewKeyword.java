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
public class ReviewKeyword {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "review_id")
	private UserReview userReview;

	@ManyToOne
	@JoinColumn(name = "keyword_id")
	private Keyword keyword;

	@Builder
	public ReviewKeyword(UserReview userReview, Keyword keyword) {
		this.userReview = userReview;
		this.keyword = keyword;
	}

	public ReviewKeyword() {
	}
}
