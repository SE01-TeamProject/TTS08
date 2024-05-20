package com.example.demo.db;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import lombok.Getter;

@Getter
@Entity
@Table(name="Issue")
public class table_Issue {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="PID",referencedColumnName="pid")
    private table_Project pid;

    @Column(length = 10)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name="reporter",referencedColumnName="id")
    private table_User reporter;
    
    @ManyToOne
    @JoinColumn(name="assignee",referencedColumnName="id")
    private table_User assignee;

    private int priority;
    private int status;
    private int type;

    @Column(columnDefinition = "DATE")
    private LocalDateTime dateTime;

}
