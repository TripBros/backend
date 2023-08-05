package com.example.tripbros.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class UserReview {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "target_user_id")
	private User user;

	private Long FromUserId;
	private float star;
	@Column(columnDefinition = "TEXT")
	private String content;

	@OneToMany(mappedBy = "userReview")
	private List<ReviewKeyword> reviewKeyword;

	@Builder
	public UserReview(User user, Long fromUserId, float star, String content, List<ReviewKeyword> reviewKeyword) {
		this.user = user;
		FromUserId = fromUserId;
		this.star = star;
		this.content = content;
		this.reviewKeyword = reviewKeyword;
	}

	public UserReview() {
	}
}
