package com.sillock.domain.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sillock.common.dto.ResponseDto;
import com.sillock.common.message.ResponseMessage;
import com.sillock.core.auth.jwt.dto.TokenDto;
import com.sillock.domain.member.model.dto.MemberDto;
import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.member.service.MemberService;
import com.sillock.domain.sillog.model.component.SillogMapper;
import com.sillock.domain.sillog.model.dto.SillogDto;
import com.sillock.domain.sillog.model.entity.Sillog;
import com.sillock.domain.sillog.service.SillogService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;
    private final SillogService sillogService;
    private final SillogMapper sillogMapper;

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

    @GetMapping("/test")
    public ResponseEntity<MemberDto> test(){
        MemberDto memberDto = MemberDto.builder().memberId(1L).email("test@gmail.com").name("treering").identifier("test").uniqueCode("1234").build();
        return ResponseEntity.ok(memberDto);
    }

    @GetMapping(value = "/{memberId}/sillogs")
    @ResponseBody
    public ResponseDto<List<SillogDto>> readSillogList(@PathVariable String memberId) {
        List<Sillog> sillogList = sillogService.getSillogList(memberId);
        List<SillogDto> sillogDtoList = sillogList.stream().map(sillogMapper::toDto).collect(Collectors.toList());
        return ResponseDto.of(HttpStatus.OK, ResponseMessage.READ_EVENT, sillogDtoList);
    }

    @GetMapping(value = "/{memberId}/sillogs/{title}")
    @ResponseBody
    public ResponseDto<List<SillogDto>> readSillogListBytitle(@PathVariable String memberId, @PathVariable String title) {
        List<Sillog> sillogListByTitle = sillogService.findSillogTitle(memberId, title);
        List<SillogDto> sillogDtoListByTitle = sillogListByTitle.stream().map(sillogMapper::toDto).collect(Collectors.toList());
        return ResponseDto.of(HttpStatus.OK, ResponseMessage.READ_EVENT, sillogDtoListByTitle);
    }

    @AllArgsConstructor
    public class MemberCheckDto {
        private boolean exist;
    }

}