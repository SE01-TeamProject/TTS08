package com.example.demo.db;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import lombok.Getter;

@Getter
@Entity
@Table(name = "Comment")
public class table_Comment {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cid;

    @ManyToOne
    @JoinColumn(name="Issue",referencedColumnName="id")
    private table_Issue Issue_id;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column(columnDefinition = "DATE")
    private LocalDateTime Date;

}
