package com.example.demo.db;

import jakarta.persistence.*;

import lombok.Getter;

@Getter
@Entity
@Table(name = "ProjectToUser")
public class table_Proj_to_User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
    @ManyToOne
	@JoinColumn(name="PID",referencedColumnName="pid")
    private table_Project pid;
	
    @ManyToOne
    @JoinColumn(name="UID",referencedColumnName="id")
    private table_User uid;
}
