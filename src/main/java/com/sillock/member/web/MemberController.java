package com.sillock.member.web;

import com.sillock.member.entity.Member;
import com.sillock.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

//    @GetMapping(value = "/{memberId}",produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Member> getMember(Long memberId){
        Optional<Member> member = memberService.findbymemberId(memberId);
        return new ResponseEntity<Member>(member.get(), HttpStatus.OK);
    }

}