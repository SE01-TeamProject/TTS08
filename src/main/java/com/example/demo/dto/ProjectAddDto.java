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
	private String developer1;
	private String developer2;
	private String developer3;
	private String tester1;
	private String tester2;
	private String tester3;

}
