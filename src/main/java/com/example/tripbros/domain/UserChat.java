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
public class UserChat {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "chat_id")
	private ChatRoom chatRoom;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Builder
	public UserChat(ChatRoom chatRoom, User user) {
		this.chatRoom = chatRoom;
		this.user = user;
	}

	public UserChat() {
	}
}
