package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.domain.Comment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentDto {
	
    private Integer writer;
    private String note;
    private LocalDateTime date;
    
    public static CommentDto from(Comment comment) {
    	return new CommentDto(comment.getWriter(), comment.getNote(), comment.getDate());
    }
    
    public static CommentDto of(Integer writer, String note, LocalDateTime date) {
    	return new CommentDto(writer, note, date);
    }
}
