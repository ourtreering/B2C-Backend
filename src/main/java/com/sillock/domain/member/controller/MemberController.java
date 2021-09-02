package com.sillock.domain.member.controller;

import com.sillock.common.dto.ResponseDto;
import com.sillock.common.message.ResponseMessage;
import com.sillock.core.annotation.CurrentUser;
import com.sillock.core.auth.jwt.component.JwtCreator;
import com.sillock.core.auth.jwt.model.SocialProfile;
import com.sillock.core.auth.jwt.model.TokenDto;
import com.sillock.domain.member.model.component.MemberMapper;
import com.sillock.domain.member.model.dto.MemberProfile;
import com.sillock.domain.member.model.dto.MemberSignUp;
import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.member.service.MemberAuthService;
import com.sillock.domain.member.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberAuthService memberAuthService;
    private final MemberMapper memberMapper;
    private final JwtCreator jwtCreator;

    @GetMapping(value = "/me")
    public ResponseEntity<ResponseDto<MemberProfile>> getMyProfile(@CurrentUser Member member){
        return ResponseEntity.status(HttpStatus.OK)
               .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.READ_MEMBER_PROFILE, memberMapper.toDtoFromMemberEntity(member)));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<?>> loginByProvider(@RequestBody SocialProfile profile) {
        Member member = memberAuthService.login(profile.getEmail());

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.LOGIN_SUCCESS,
                        new TokenDto(jwtCreator.createAccessToken(member), null)));
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto<?>> signUp(@RequestBody MemberSignUp memberSignUp) {
        Member newMember = memberMapper.toEntityFromMemberSignUp(memberSignUp);

        Member member = memberAuthService.signup(newMember);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseDto.of(HttpStatus.CREATED, ResponseMessage.SIGN_UP_SUCCESS,
                        new TokenDto(jwtCreator.createAccessToken(member), null)));
    }

    @PatchMapping("/me")
    public ResponseEntity<ResponseDto<MemberProfile>> updateProfile(@CurrentUser Member member, @RequestBody MemberProfile memberProfile){
        memberMapper.updateProfile(memberProfile, member);
        memberService.updateProfile(member);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.UPDATE_MEMBER_PROFILE,
                        memberMapper.toDtoFromMemberEntity(member)));
    }

    @DeleteMapping("/me")
    public ResponseEntity<ResponseDto<?>> deleteMember(@CurrentUser Member member){
        memberService.deleteMember(member);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(ResponseDto.of(HttpStatus.NO_CONTENT, ResponseMessage.DELETED));
    }

    @AllArgsConstructor
    public class MemberCheckDto {
        private boolean exist;
    }

}