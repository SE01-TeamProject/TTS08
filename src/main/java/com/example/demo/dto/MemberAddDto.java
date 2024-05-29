package com.example.demo.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberAddDto {

	private String name;
    private String fullName;
    private String password;
    private String level;

}
