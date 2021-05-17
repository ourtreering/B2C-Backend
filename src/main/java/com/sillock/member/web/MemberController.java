package com.sillock.member.web;

import com.sillock.member.entity.Member;
import com.sillock.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //회원번호로 회원 한명 조회
    @GetMapping(value = "/{memberId}",produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Member> getMember(@PathVariable Long memberId){
        Member member = memberService.findById(memberId);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    //회원 모두 부르기
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<Member>> getAllmembers(){
        List<Member> member = memberService.findAll();
        return new ResponseEntity<>(member, HttpStatus.OK);
    }


}