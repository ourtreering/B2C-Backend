package com.sillock.domain.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sillock.common.dto.ResponseDto;
import com.sillock.common.message.ResponseMessage;
import com.sillock.common.object.BuilderObjects;
import com.sillock.core.auth.jwt.dto.TokenDto;
import com.sillock.domain.member.model.dto.MemberDto;
import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.member.service.MemberService;
import com.sillock.domain.sillog.model.component.SillogMapper;
import com.sillock.domain.sillog.model.dto.SillogBySequenceDto;
import com.sillock.domain.sillog.model.dto.SillogDto;
import com.sillock.domain.sillog.model.entity.Sillog;
import com.sillock.domain.sillog.service.SillogService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;
    private final SillogService sillogService;
    private final SillogMapper sillogMapper;
    BuilderObjects builderObjects = new BuilderObjects();

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

    /*실록태그 생기면 사용할 코드 - sillogService 코드도 동일*/
//    @GetMapping(value = "/{memberId}/sillogs")
//    @ResponseBody
//    public ResponseDto<List<SillogDto>> readSillogList(@PathVariable Long memberId,
//    @RequestParam(required = false) String title, @RequestParam(required = false) String tag) {
//        List<Sillog> sillogListByTitle = sillogService.findSillogList(memberId, title, tag);
//        List<SillogDto> sillogDtoListByTitle = sillogListByTitle.stream().map(sillogMapper::toDto).collect(Collectors.toList());
//        return ResponseDto.of(HttpStatus.OK, ResponseMessage.READ_EVENT, sillogDtoListByTitle);
//    }

    /*실록 태그가 없어서 임시로 사용하는 코드*/
    @GetMapping(value = "/{memberId}/sillogs")
    @ResponseBody
    public ResponseDto<List<SillogDto>> readSillogList(@PathVariable Long memberId, @RequestParam(required = false) String title) {
        Sillog sillog = new Sillog();
        if(title == null) sillog = builderObjects.customSillog(memberId, "제목");
        else sillog = builderObjects.customSillog(memberId, title);
        List<Sillog> sillogListByTitle = Arrays.asList(sillog,sillog);
        List<SillogDto> sillogDtoListByTitle = sillogListByTitle.stream().map(sillogMapper::toDto).collect(Collectors.toList());
        return ResponseDto.of(HttpStatus.OK, ResponseMessage.READ_EVENT, sillogDtoListByTitle);
    }

    @GetMapping(value = "/{memberId}/sillogs/{title}/{sequence}")
    @ResponseBody
    public ResponseDto<SillogBySequenceDto> readSillogListBySequence(@PathVariable Long memberId, @PathVariable String title, @PathVariable int sequence) {
        SillogBySequenceDto sillogBySequenceDto = sillogService.findSillogBySequence(memberId, title, sequence);
        return ResponseDto.of(HttpStatus.OK, ResponseMessage.READ_EVENT,sillogBySequenceDto);
    }

    @AllArgsConstructor
    public class MemberCheckDto {
        private boolean exist;
    }

}