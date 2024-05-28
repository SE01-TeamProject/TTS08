package com.example.demo.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Issue;
import com.example.demo.domain.Member;
import com.example.demo.dto.IssueAddDto;
import com.example.demo.dto.IssueSetDto;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.IssueRepository;
import com.example.demo.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class IssueService {
	private final IssueRepository issueRepository;
	private final MemberRepository memberRepository;
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	
	public Issue findById(Integer id) {
		return issueRepository.findById(id).get();
	}
	
	// 우선순위와 상태를 변경하는 메소드
	public void setState(IssueSetDto issueSetDto) {
		Issue issue = issueRepository.findById(issueSetDto.getId()).get();
		issue.setState(issueSetDto.getPriority(), issueSetDto.getStatus());
		issueRepository.save(issue);
	}
	
	// Assignee를 변경하는 메소드
//	public void setAssignee(IssueSetDto issueSetDto) {
//		Issue issue = issueRepository.findById(issueSetDto.getId()).get();
//		issue.setAssignee(issueSetDto.getAssignee());
//		issueRepository.save(issue);
//	}
	
	// 이슈를 추가하는 메소드
	public String addIssue(IssueAddDto issueAddDto) {
		Issue issue = issueRepository.findByTitle(issueAddDto.getTitle());
		if (issue != null) {
			return "false";
		} else {
			issueRepository.save(new Issue(issueAddDto.getTitle(), issueAddDto.getDescription()));
			//int priority = Issue.getPriorityFromString(issueAddDto.getPriority());
			//int status = Issue.getStatusFromString(issueAddDto.getStatus());
			//int type = Issue.getTypeFromString(issueAddDto.getType());
			//issueRepository.save(new Issue(issueAddDto.getTitle(), issueAddDto.getDescription(), issueAddDto.getReporter(), issueAddDto.getAssignee(), priority, status, type));
		}
		return "true";
	}
	
	// 그냥 이슈의 리스트 나열
//	public List<Issue> getIssueList() {
//		List<Issue> issues = new ArrayList<Issue>();
//		issueRepository.findAll().forEach(item -> issues.add(item));
//		return issues;
//	}
	
	public String getIssueList() {
		JSONArray issues = new JSONArray();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		issueRepository.findAll().forEach(item -> {
			JSONObject obj = new JSONObject();
			obj.put("id", item.getId());
			obj.put("title", item.getTitle());
			obj.put("description", item.getDescription());
			obj.put("date", item.getDate().format(formatter));
			issues.put(obj);
		});
		return issues.toString();
	}
	
	// 고유 id를 받고 해당 이슈 정보를 가져오는 메소드
	public String getIssue(Integer id) {
		Optional<Issue> issue = issueRepository.findById(id);
		if (issue.isEmpty()) return "";
		
		JSONObject obj = new JSONObject();
		Optional<Member> user;
		obj.put("title", issue.get().getTitle());
		obj.put("description", issue.get().getDescription());
		user = memberRepository.findById(issue.get().getReporter());
		obj.put("reporter", user.isEmpty() ? "N/A" : user.get().getName());
		user = memberRepository.findById(issue.get().getAssignee());
		obj.put("assignee", user.isEmpty() ? "N/A" : user.get().getName());
		obj.put("priority", issue.get().getPriority());
		obj.put("status", issue.get().getStatus());
		obj.put("type", issue.get().getType());
		obj.put("date", issue.get().getDate().format(formatter));
		return obj.toString();
	}

	// 프로젝트 id를 받고 해당 이슈들을 가져오는 메소드
	public String getIssueList(Integer pid) {
		JSONArray issues = new JSONArray();
		issueRepository.findAll().forEach(item -> {
			if (item.getProject() == pid) {
				JSONObject obj = new JSONObject();
				Optional<Member> user;
				obj.put("title", item.getTitle());
				obj.put("description", item.getDescription());
				user = memberRepository.findById(item.getReporter());
				obj.put("reporter", user.isEmpty() ? "N/A" : user.get().getName());
				user = memberRepository.findById(item.getAssignee());
				obj.put("assignee", user.isEmpty() ? "N/A" : user.get().getName());
				obj.put("priority", item.getPriority());
				obj.put("status", item.getStatus());
				obj.put("type", item.getType());
				obj.put("date", item.getDate().format(formatter));
				issues.put(obj);
			}
		});
		return issues.toString();
	}
}
