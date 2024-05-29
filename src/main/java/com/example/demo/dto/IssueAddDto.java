package com.example.demo.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IssueAddDto {

	private String project;
	private String title;
    private String description;
    private String reporter;
    private String priority;
    private String type;

}
