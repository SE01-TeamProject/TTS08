package com.example.demo.domain;

import java.util.Date;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
public class Comment {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_writer")
	private User writer;	// 코멘트 작성자
	private String comment;	// 코멘트 내용
	private Date date;		// 코멘트 작성 날짜
	
	public Comment() {
    }

    public Comment(User writer, String comment, Date date) {
        this.writer = writer;
        this.comment = comment;
        this.date = date;
    }
}
