package com.example.demo.dto;

import com.example.demo.domain.Member;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberDto {
	
    private String name;
    private String fullName;
    private String password;
    private int level;
    
    public static MemberDto from(Member member) {
    	return new MemberDto(member.getName(), member.getFullName(), member.getPassword(), member.getLevel());
    }
    
    public static MemberDto of(String name, String fullName, String password, int level) {
    	return new MemberDto(name, fullName, password, level);
    }
}
