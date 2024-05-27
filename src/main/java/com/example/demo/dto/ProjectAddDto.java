package com.example.demo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectAddDto {

	private String title;
    private String description;
    private Integer PL;
	private Integer developer;
	private Integer tester;

}
