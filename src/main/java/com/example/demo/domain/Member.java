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
    private Integer id;			// 사용자 고유 id값

    @Column(name = "NAME")
    private String name;		// 사용자 이름(접속 id)
    
    @Column(name = "FULLNAME")
    private String fullName;	// 사용자 본명
    
    @Column(name = "PASSWORD")
    private String password;	// 사용자 비밀번호
    
    @Column(name = "LEVEL")
    private int level;			// 사용자 권한 레벨

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
	
	// 입력받은 권한의 이름을 문자열로 받아 int값으로 변환
	public static int getLevelFromString(String level) {
		switch(level) {
		case "PL":
			return Level.PL.ordinal();			// 1
		case "Developer":
			return Level.DEVELOPER.ordinal();	// 2
		case "Tester":
			return Level.TESTER.ordinal();		// 3
		}
		return 0;
	}
	
	public static String getStringFromLevel(int level) {
		switch(level) {
		case 0:
			return "Admin";
		case 1:
			return "PL";
		case 2:
			return "Developer";
		case 3:
			return "Tester";
		}
		return null;
	}

}
