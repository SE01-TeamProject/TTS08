package com.example.demo.db;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="Log")
public class table_Log {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="PID",referencedColumnName="pid")
    private table_Project project_id;

    @Column(columnDefinition = "TEXT")
    private String log;

    @Column(columnDefinition = "DATE")
    private LocalDateTime date;
}
