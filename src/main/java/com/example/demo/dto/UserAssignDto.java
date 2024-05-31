package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserAssignDto {
    private String projectTitle;
    private String username;

    @Builder
    public UserAssignDto(String projectTitle, String username) {
        this.projectTitle = projectTitle;
        this.username = username;
    }
}
