package com.sillock.domain.member.controller;

import com.sillock.common.dto.ResponseDto;
import com.sillock.core.annotation.CurrentUser;
import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.sillog.model.dto.SillogResponseDto;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

//    private final MemberService memberService;
//    private final SillogService sillogService;
//    private final SillogMapper sillogMapper;
//    BuilderObjects builderObjects = new BuilderObjects();
//
//    @GetMapping(value = "/{memberId}")
//    public ResponseEntity<Member> getMember(@PathVariable String memberId){
//        Member member = memberService.findById(memberId);
//        return new ResponseEntity<>(member, HttpStatus.OK);
//    }
//
//    @PostMapping("/exist/{provider}")
//    public ResponseEntity<MemberCheckDto> isExistMember(@RequestBody TokenDto token, @PathVariable String provider) throws JsonProcessingException {
//        boolean isExist = memberService.isExistMemberByProvider(token.getAccessToken(), provider);
//
//        return ResponseEntity.ok(new MemberCheckDto(isExist));
//    }

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
//    @AllArgsConstructor
//    public class MemberCheckDto {
//        private boolean exist;
//    }

}