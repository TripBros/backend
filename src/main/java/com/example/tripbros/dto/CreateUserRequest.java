package com.example.tripbros.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class CreateUserRequest {
    private String email;
    private String nickname;
    private int age;
    private String gender;
    private String travelStyle;
    private String profileImage;
}
