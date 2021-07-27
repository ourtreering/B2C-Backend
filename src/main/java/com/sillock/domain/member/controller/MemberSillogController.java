package com.sillock.domain.member.controller;

import com.sillock.common.dto.ResponseDto;
import com.sillock.common.message.ResponseMessage;
import com.sillock.core.annotation.CurrentUser;
import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.sillog.model.component.SillogMapper;
import com.sillock.domain.sillog.model.dto.SillogElementDto;
import com.sillock.domain.sillog.model.dto.SillogResponseDto;
import com.sillock.domain.sillog.model.dto.SillogTitleDto;
import com.sillock.domain.sillog.model.entity.Sillog;
import com.sillock.domain.sillog.model.entity.SillogTitle;
import com.sillock.domain.sillog.service.SillogService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberSillogController {

    private final SillogService sillogService;
    private final SillogMapper sillogMapper;

    // TODO: 페이징 기능이 적용되어야 함 https://medium.com/@davide.pedone/cursor-based-pagination-with-spring-boot-and-mongodb-bca6446f3b1f
    @GetMapping(value = "/{memberId}/sillogs")
    @ResponseBody
    public ResponseEntity<ResponseDto<List<SillogElementDto>>> getMemberSillogList(@CurrentUser Member member, @PathVariable ObjectId memberId) {
        List<Sillog> sillogList = sillogService.getMemberSillogList(memberId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.READ_SILLOG_LIST,
                        sillogList.stream().map(sillogMapper::toSillogElementDtoFromEntity)
                        .collect(Collectors.toList())
                ));
    }

    @GetMapping(value = "/{memberId}/sillogs/title")
    @ResponseBody
    public ResponseEntity<ResponseDto<List<SillogTitleDto>>> getMemberSillogTitleList(@CurrentUser Member member, @PathVariable ObjectId memberId){
        List<SillogTitle> sillogTitleList = sillogService.getMemberSillogTitleList(memberId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.READ_SILLOG_TITLE_LIST,
                        sillogTitleList.stream().map(sillogMapper::toSillogTitleDtoFromSillogTitle).collect(Collectors.toList())
                ));
    }

//    @GetMapping(value = "/{memberId}/sillogs/search")
//    @ResponseBody
//    public ResponseEntity<ResponseDto<List<SillogResponseDto>>> searchMemberSillogListByTag(@CurrentUser Member member, @PathVariable ObjectId memberId, ) {
//
//       return ResponseEntity.status(HttpStatus.OK)
//               .body()
//    }
}
