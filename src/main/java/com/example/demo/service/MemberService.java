package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Member;
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
	
	public boolean login(MemberLoginDto memberLoginDto) {
		// MemberEntity타입의 객체 생성 후 jpa의 findBy 메서드 호출 및 정보 저장
		// MemberLoginDTO의 memberEmail을 보내 값을 memberEntity에 담는것임.
		Member member = memberRepository.findByName(memberLoginDto.getName());
		// 정보가 비어있으면 로그인시도를 한 email을 가진 데이터 자체가 없는 정보라는 뜻임.
		if (member != null) {
			// 로그인을 시도한 데이터의 비밀번호와 jpa에서 받아온 데이터의 비밀번호를 비교
			if(member.getPassword().equals(memberLoginDto.getPassword())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
