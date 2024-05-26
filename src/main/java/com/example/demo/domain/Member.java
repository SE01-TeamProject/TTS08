package com.example.demo.domain;

import com.example.demo.domain.mapping.MemberAssignedPr;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@NoArgsConstructor
@Getter
public class Member {
	
	public enum Level {
		ADMIN,
		PL,
		DEVELOPER,
		TESTER
	}

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;
    
    @Column(name = "FULLNAME")
    private String fullName;
    
    @Column(name = "PASSWORD")
    private String password;
    
    @Column(name = "LEVEL")
    private int level;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberAssignedPr> memberAssignedPrs = new ArrayList<>();
    @Builder
    public Member(String name, String fullName, String password, int level) {
        this.name = name;
        this.fullName = fullName;
        this.password = password;
        this.level = level;
    }
    
	public static Member createMember(String name, String fullName, String password, int level) {
    	Member member = new Member(name, fullName, password, level);
    	return member;
    }

}
