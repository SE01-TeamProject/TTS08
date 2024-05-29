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
import com.example.demo.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class IssueService {
	private final IssueRepository issueRepository;
	private final MemberRepository memberRepository;
	private final ProjectRepository projectRepository;
	
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
	public void setAssignee(IssueSetDto issueSetDto) {
		Issue issue = issueRepository.findById(issueSetDto.getId()).get();
		issue.setAssignee(memberRepository.findByName(issueSetDto.getAssignee()).getId());
		issueRepository.save(issue);
	}
	
	// 이슈를 추가하는 메소드
	public String addIssue(IssueAddDto issueAddDto) {
		Issue issue = issueRepository.findByTitle(issueAddDto.getTitle());
		if (issue != null) {
			return "false";
		} else {
			int priority = Issue.getPriorityFromString(issueAddDto.getPriority());
			int type = Issue.getTypeFromString(issueAddDto.getType());
			issueRepository.save(new Issue(projectRepository.findByTitle(issueAddDto.getProject()).getId(),
					issueAddDto.getTitle(), 
					issueAddDto.getDescription(), 
					memberRepository.findByName(issueAddDto.getReporter()).getId(),
					priority, type));
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
				obj.put("issuenum", item.getId());
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
	
	// 이슈 트렌드 분석: 이슈의 Priority, Status, Type가 몇 개인지 JSONString으로 보내는 메소드
	/*
	 TODO: 월별 통계를 하나의 함수로 한번에 가져갈지
	 		별개의 함수로 만들어 따로 가져갈지 선택
	 */
	public String getIssueTrend() {
		JSONObject trend = new JSONObject();
		{
			JSONObject obj = new JSONObject();
			obj.put("Major", issueRepository.countByPriority(Issue.Priority.MAJOR.ordinal()));
			obj.put("Minor", issueRepository.countByPriority(Issue.Priority.MINOR.ordinal()));
			obj.put("Blocker", issueRepository.countByPriority(Issue.Priority.BLOCKER.ordinal()));
			obj.put("Critical", issueRepository.countByPriority(Issue.Priority.CRITICAL.ordinal()));
			obj.put("Trivial", issueRepository.countByPriority(Issue.Priority.TRIVIAL.ordinal()));
			trend.put("Priority", obj);	
		}
		{
			JSONObject obj = new JSONObject();
			obj.put("New", issueRepository.countByStatus(Issue.Status.NEW.ordinal()));
			obj.put("Assigned", issueRepository.countByStatus(Issue.Status.ASSIGNED.ordinal()));
			obj.put("Resolved", issueRepository.countByStatus(Issue.Status.RESOLVED.ordinal()));
			obj.put("Closed", issueRepository.countByStatus(Issue.Status.CLOSED.ordinal()));
			obj.put("Reopened", issueRepository.countByStatus(Issue.Status.REOPENED.ordinal()));
			trend.put("Status", obj);	
		}
		{
			JSONObject obj = new JSONObject();
			obj.put("Bug", issueRepository.countByType(Issue.Type.BUG.ordinal()));
			obj.put("Task", issueRepository.countByType(Issue.Type.TASK.ordinal()));
			trend.put("Type", obj);	
		}
		return trend.toString();	
	}
	
	// 이슈 월별 분석: 이슈가 해당 달에 몇 개 생성됐는지 JSONString으로 보내는 메소드
	public String getIssueStatics() {
		JSONObject statics = new JSONObject();
		return statics.toString();
	}
	
}
