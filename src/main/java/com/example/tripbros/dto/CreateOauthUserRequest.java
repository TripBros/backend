package com.example.tripbros.dto;

import com.example.tripbros.domain.enumerate.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CreateOauthUserRequest {
    private String email;
    private String gender;
    private Integer ageStart;
    private Integer ageEnd;
}
