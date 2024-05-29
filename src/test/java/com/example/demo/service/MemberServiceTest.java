package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.domain.Member;
import com.example.demo.dto.MemberAddDto;
import com.example.demo.dto.MemberLoginDto;
import com.example.demo.repository.MemberRepository;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    void findById_ShouldReturnMember_WhenMemberExists() {
        // 준비
        Member member = new Member("testName", "testFullName", "testPassword", 1);
        when(memberRepository.findById(any())).thenReturn(Optional.of(member));

        // 실행
        Member foundMember = memberService.findById(1);

        // 검증
        assertThat(foundMember).isNotNull();
        assertThat(foundMember.getName()).isEqualTo("testName");
    }

    @Test
    void login_ShouldReturnTrue_WhenCredentialsAreCorrect() {
        // 준비
        Member member = new Member("testName", "testFullName", "testPassword", 1);
        when(memberRepository.findByName(anyString())).thenReturn(member);

        MemberLoginDto loginDto = new MemberLoginDto("testName", "testPassword");

        // 실행
        String result = memberService.login(loginDto);

        // 검증
        assertThat(result).isEqualTo("true");
    }

    @Test
    void login_ShouldReturnFalse_WhenCredentialsAreIncorrect() {
        // 준비
        Member member = new Member("testName", "testFullName", "testPassword", 1);
        when(memberRepository.findByName(anyString())).thenReturn(member);

        MemberLoginDto loginDto = new MemberLoginDto("testName", "wrongPassword");

        // 실행
        String result = memberService.login(loginDto);

        // 검증
        assertThat(result).isEqualTo("false");
    }

    @Test
    void addUser_ShouldReturnTrue_WhenMemberDoesNotExist() {
        // 준비
        when(memberRepository.findByName(anyString())).thenReturn(null);
        MemberAddDto memberAddDto = new MemberAddDto("newName", "newFullName", "newPassword", "PL");

        // 실행
        String result = memberService.addUser(memberAddDto);

        // 검증
        assertThat(result).isEqualTo("true");
    }

    @Test
    void addUser_ShouldReturnFalse_WhenMemberAlreadyExists() {
        // 준비
        Member member = new Member("existingName", "existingFullName", "existingPassword", 1);
        when(memberRepository.findByName(anyString())).thenReturn(member);
        MemberAddDto memberAddDto = new MemberAddDto("existingName", "newFullName", "newPassword", "PL");

        // 실행
        String result = memberService.addUser(memberAddDto);

        // 검증
        assertThat(result).isEqualTo("false");
    }

    @Test
    void getMemberList_ShouldReturnAllMembers() {
        // 준비
        List<Member> members = new ArrayList<>();
        members.add(new Member("name1", "fullName1", "password1", 1));
        members.add(new Member("name2", "fullName2", "password2", 2));
        when(memberRepository.findAll()).thenReturn(members);

        // 실행
        List<Member> memberList = memberService.getMemberList();

        // 검증
        assertThat(memberList).hasSize(2);
    }

    @Test
    void getMember_ShouldReturnMemberInfo_WhenMemberExists() {
        // 준비
        Member member = new Member("testName", "testFullName", "testPassword", 1);
        when(memberRepository.findById(any())).thenReturn(Optional.of(member));

        // 실행
        String memberInfo = memberService.getMember(1);

        // 검증
        JSONObject obj = new JSONObject();
        obj.put("name", member.getName());
        obj.put("fullName", member.getFullName());
        obj.put("level", member.getLevel());

        assertThat(memberInfo).isEqualTo(obj.toString());
    }

    @Test
    void getMember_ShouldReturnEmptyString_WhenMemberDoesNotExist() {
        // 준비
        when(memberRepository.findById(any())).thenReturn(Optional.empty());

        // 실행
        String memberInfo = memberService.getMember(1);

        // 검증
        assertThat(memberInfo).isEqualTo("");
    }
}
