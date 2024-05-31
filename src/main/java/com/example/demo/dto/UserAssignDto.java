package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserAssignDto {
    private String projectTitle;
    private String username;
    private String level; // String으로 받아서 변환

    @Builder
    public UserAssignDto(String projectTitle, String username, String level) {
        this.projectTitle = projectTitle;
        this.username = username;
        this.level = level;
    }
}
