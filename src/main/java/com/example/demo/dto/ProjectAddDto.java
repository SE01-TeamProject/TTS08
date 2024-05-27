package com.example.demo.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectAddDto {

	private String title;
    private String description;
    private String PL;
	private String developer;
	private String tester;

}
