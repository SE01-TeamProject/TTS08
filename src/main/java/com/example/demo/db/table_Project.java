package com.example.demo.db;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "Project")
public class table_Project {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="PID")
    private long pid;

    @Column(length = 10)
    private String title;
}
