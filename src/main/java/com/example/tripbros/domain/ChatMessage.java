package com.example.tripbros.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class ChatMessage {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "chatroom_id")
	private ChatRoom chatRoom;

	@Column(columnDefinition = "TEXT")
	private String content;
	private LocalDateTime chatTime;

	@Builder
	public ChatMessage(User user, ChatRoom chatRoom, String content, LocalDateTime chatTime) {
		this.user = user;
		this.chatRoom = chatRoom;
		this.content = content;
		this.chatTime = chatTime;
	}

	public ChatMessage() {
	}
}
