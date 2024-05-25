package com.example.demo.db;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "IST_User")
public class table_User {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length=20)
    private String id;

    @Column(length = 10)
    private String username;
    
    @Column(length = 10)
    private String password;
    
    private int level;
}
