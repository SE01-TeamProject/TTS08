package com.example.demo.domain;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment")
@NoArgsConstructor
@Getter
public class Comment {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;		// 코멘트 고유 id
    
    @Column(name = "WRITER")
	private Integer writer;	// 코멘트 작성자 - 사용자 id(Member 도메인의 name)으로 받음
    
    @Column(name = "NOTE")
    private String note;	// 코멘트 내용
    
    @Column(name = "DATE")	// 코멘트 작성 날짜
    private LocalDateTime date;
    
    private Integer issue;	// 코멘트가 소속된 이슈의 id

    /*@ManyToOne
    @JoinColumn(name = "issue_id", nullable = false)
    private Issue issue;*/
    
    @Builder
    public Comment(Integer writer, String note) {
    	this.writer = writer;
    	this.note = note;
        this.date = LocalDateTime.now();
    }
    
	public static Comment createComment(Integer writer, String note) {
		Comment comment = new Comment(writer, note);
    	return comment;
    }
}
