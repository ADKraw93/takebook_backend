package com.project.takebook.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDto {
    private Long Id;
    private String username;
    private String email;
    private String password;
    private double budget;
}
