package com.sillock.domain.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sillock.common.dto.ResponseDto;
import com.sillock.common.message.ResponseMessage;
import com.sillock.core.annotation.CurrentUser;
import com.sillock.core.auth.jwt.component.JwtCreator;
import com.sillock.core.auth.jwt.model.SocialProfile;
import com.sillock.core.auth.jwt.model.TokenDto;
import com.sillock.domain.member.model.component.MemberMapper;
import com.sillock.domain.member.model.dto.MemberProfile;
import com.sillock.domain.member.model.entity.Member;
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
    private final MemberMapper memberMapper;
    private final JwtCreator jwtCreator;

    @GetMapping(value = "/me")
    public ResponseEntity<ResponseDto<MemberProfile>> getMyProfile(@CurrentUser Member member){
        return ResponseEntity.status(HttpStatus.OK)
               .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.READ_MEMBER_PROFILER, memberMapper.toDtoFromMemberEntity(member)));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<?>> loginByProvider(@RequestBody SocialProfile profile) {
        Member member = memberService.findByMemberByEmail(profile.getEmail());
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.LOGIN_SUCCESS,
                        new TokenDto(jwtCreator.createAccessToken(member), null)));
    }


//    @GetMapping("/test")
//    public ResponseEntity<MemberDto> test(){
//        MemberDto memberDto = MemberDto.builder().memberId(1L).email("test@gmail.com").nickName("treering").build();
//        return ResponseEntity.ok(memberDto);
//    }
//
//    /*실록태그 생기면 사용할 코드 - sillogService 코드도 동일*/
////    @GetMapping(value = "/{memberId}/sillogs")
////    @ResponseBody
////    public ResponseDto<List<SillogDto>> readSillogList(@PathVariable Long memberId,
////    @RequestParam(required = false) String title, @RequestParam(required = false) String tag) {
////        List<Sillog> sillogListByTitle = sillogService.findSillogList(memberId, title, tag);
////        List<SillogDto> sillogDtoListByTitle = sillogListByTitle.stream().map(sillogMapper::toDto).collect(Collectors.toList());
////        return ResponseDto.of(HttpStatus.OK, ResponseMessage.READ_EVENT, sillogDtoListByTitle);
////    }
//
    /*실록 태그가 없어서 임시로 사용하는 코드*/
//    @GetMapping(value = "/{memberId}/sillogs")
//    @ResponseBody
//    public ResponseEntity<ResponseDto<List<SillogResponseDto>>> readSillogList(@CurrentUser Member member, @PathVariable ObjectId memberId) {
//
//    }
//
//
    @AllArgsConstructor
    public class MemberCheckDto {
        private boolean exist;
    }

}