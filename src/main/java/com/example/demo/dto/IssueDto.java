package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.domain.Issue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IssueDto {

	private String title;
	private String description;
    private Integer reporter;
    private Integer assignee;
	private int priority;
	private int status;
	private int type;
    private LocalDateTime date;
    
    public static IssueDto from(Issue issue) {
    	return new IssueDto(issue.getTitle(), issue.getDescription(), issue.getReporter(), issue.getAssignee(), issue.getPriority(), issue.getStatus(), issue.getType(), issue.getDate());
    }
    
    public static IssueDto of(String title, String description, Integer reporter, Integer assignee, int priority, int status, int type, LocalDateTime date) {
    	return new IssueDto(title, description, reporter, assignee, priority, status, type, date);
    }
}
