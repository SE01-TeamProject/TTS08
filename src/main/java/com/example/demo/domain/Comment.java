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
    private Integer id;
    
    @Column(name = "WRITER")
	private Integer writer;
    
    @Column(name = "NOTE")
    private String note;
    
    @Column(name = "DATE")
    private LocalDateTime date;
    
    private Integer issue;

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
