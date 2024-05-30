package com.example.demo.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Member;
import com.example.demo.dto.CommentAddDto;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.IssueRepository;
import com.example.demo.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
	private final CommentRepository commentRepository;
	private final MemberRepository memberRepository;
	private final IssueRepository issueRepository;
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	
	public Comment findById(Integer id) {
		return commentRepository.findById(id).get();
	}
	
	// 코멘트를 추가하는 메소드
	public String addComment(CommentAddDto commentAddDto) {
		commentRepository.save(new Comment(issueRepository.findByTitle(commentAddDto.getIssue()).getId(), 
				memberRepository.findByName(commentAddDto.getWriter()).getId(), 
				commentAddDto.getNote()));
		return "true";
	}
	
	// 코멘트들을 리스트 형태로 가져오는 메소드
	public List<Comment> getCommentList(){
        List<Comment> comments = new ArrayList<Comment>();
        commentRepository.findAll().forEach(comment->comments.add(comment));
        return comments;
	}
	
	// 고유 id를 받고 해당 코멘트 정보를 가져오는 메소드
	public String getComment(Integer id) {
		Optional<Comment> com = commentRepository.findById(id);
		if (com.isEmpty()) return "";
		
		JSONObject obj = new JSONObject();
		Optional<Member> user = memberRepository.findById(com.get().getWriter());
		obj.put("writer", user.isEmpty() ? "N/A" : user.get().getName());
		obj.put("note", com.get().getNote());
		obj.put("date", com.get().getDate().format(formatter));
		return obj.toString();
	}
	
	// 이슈 id를 받고 해당 코멘트들을 가져오는 메소드
	public String getCommentList(Integer iid) {
		JSONArray comments = new JSONArray();
		commentRepository.findAll().forEach(item -> {
			if (item.getIssue() == iid) {
				JSONObject obj = new JSONObject();
				Optional<Member> user = memberRepository.findById(item.getWriter());
				obj.put("writer", user.isEmpty() ? "N/A" : user.get().getName());
				obj.put("note", item.getNote());
				obj.put("date", item.getDate().format(formatter));
				comments.put(obj);
			}
		});
		return comments.toString();
	}
}
