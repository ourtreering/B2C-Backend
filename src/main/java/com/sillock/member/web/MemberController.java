package com.sillock.member.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sillock.common.dto.TokenDto;
import com.sillock.member.domain.entity.Member;
import com.sillock.member.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/{memberId}")
    public ResponseEntity<Member> getMember(@PathVariable Long memberId){
        Member member = memberService.findById(memberId);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PostMapping("/exist/{provider}")
    public ResponseEntity<MemberCheckDto> isExistMember(@RequestBody TokenDto token, @PathVariable String provider) throws JsonProcessingException {
        boolean isExist = memberService.isExistMemberByProvider(token.getAccessToken(), provider);

        return ResponseEntity.ok(new MemberCheckDto(isExist));
    }

    @AllArgsConstructor
    public class MemberCheckDto {
        private boolean exist;
    }

}