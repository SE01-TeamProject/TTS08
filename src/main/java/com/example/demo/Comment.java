package com.example.demo;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.demo.user.User;

@Component
public class Comment {
	private String comment;
	private User writer;
	private Date date;
	
	public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
