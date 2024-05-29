package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Member;
import com.example.demo.domain.Project;
import com.example.demo.dto.MemberAddDto;
import com.example.demo.dto.MemberDto;
import com.example.demo.dto.MemberLoginDto;
import com.example.demo.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
	private final MemberRepository memberRepository;
	
	public Member findById(Integer id) {
		return memberRepository.findById(id).get();
	}
	
	public String login(MemberLoginDto memberLoginDto) {
		// MemberEntity타입의 객체 생성 후 jpa의 findBy 메서드 호출 및 정보 저장
		// MemberLoginDTO의 memberId을 보내 값을 memberDomain에 담는것임.
		Member member = memberRepository.findByName(memberLoginDto.getId());
		// 정보가 비어있으면 로그인시도를 한 id를 가진 데이터 자체가 없는 정보라는 뜻임.
		if (member != null) {
			// 로그인을 시도한 데이터의 비밀번호와 jpa에서 받아온 데이터의 비밀번호를 비교
			if(member.getPassword().equals(memberLoginDto.getPassword())) {
				return "true";
			} else {
				return "false";
			}
		} else {
			return "false";
		}
	}
	
	public String addUser(MemberAddDto memberAddDto) {
		Member member = memberRepository.findByName(memberAddDto.getName());
		if (member != null) {
			return "false";
		} else {
			int level = Member.getLevelFromString(memberAddDto.getLevel());
			memberRepository.save(new Member(memberAddDto.getName(), memberAddDto.getFullName(), memberAddDto.getPassword(), level));
		}
		return "true";
	}
	
	public List<Member> getMemberList(){
        List<Member> members = new ArrayList<Member>();
        memberRepository.findAll().forEach(member->members.add(member));
        return members;
	}
	
	// 고유 id를 받고 해당 유저 정보를 가져오는 메소드(비밀번호 제외)
	public String getMember(Integer id) {
		Optional<Member> mem = memberRepository.findById(id);
		if (mem.isEmpty()) return "";
		
		JSONObject obj = new JSONObject();
		Optional<Member> user;
		obj.put("name", mem.get().getName());
		obj.put("fullName", mem.get().getFullName());
		obj.put("level", mem.get().getLevel());
		return obj.toString();
	}
	
	// 이름(id)를 받고 해당 유저 정보를 가져오는 메소드(비밀번호 제외)
	public String getMember(String name) { 
		Member mem = memberRepository.findByName(name);
		return (mem == null) ? "" : getMember(mem.getId());
	}
	
//	// 사용자의 레벨만 내보내는 메소드
//	public String getMemberLevel(Integer id) {
//		Optional<Member> member = memberRepository.findById(id);
//		return member.get().getStringFromLevel(member.get().getLevel());
//	}
}
