package com.sillock.member.web;

import com.sillock.member.entity.Member;
import com.sillock.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //회원번호로 회원 한명 조회
    // @GetMapping(value = "/{memberId}",produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Member> getMember(Long memberId){
        Optional<Member> member = memberService.findbymemberId(memberId);
        return new ResponseEntity<Member>(member.get(), HttpStatus.OK);
    }

    //회원 모두 부르기
    public ResponseEntity<List<Member>> getAllmembers(){
        List<Member> member = memberService.findAll();
        return new ResponseEntity<List<Member>>(member, HttpStatus.OK);
    }
}