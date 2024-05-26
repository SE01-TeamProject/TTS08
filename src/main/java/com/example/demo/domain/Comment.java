package com.example.demo.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Integer id;
    
    @Column(name = "WRITER")
	private Integer writer;
    
    @Column(name = "NOTE")
    private String note;
    
    @Column(name = "DATE")
    private LocalDateTime date;
    
    private Integer issue;
    
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
