package com.example.demo.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IssueAddDto {

	private String title;
    private String description;
//    private String reporter;
//    private String assignee;
//    private int priority;
//    private int status;
//    private int type;
//	  private String project;

}
