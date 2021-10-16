package com.sillock.domain.sillog.controller;

import com.sillock.annotation.SillogUser;
import com.sillock.common.AbstractControllerTest;
import com.sillock.common.DtoFactory;
import com.sillock.common.EntityFactory;
import com.sillock.domain.sillog.model.component.SillogMapper;
import com.sillock.domain.sillog.model.dto.SillogPostDto;
import com.sillock.domain.sillog.model.entity.Sillog;
import com.sillock.domain.sillog.service.SillogService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static com.sillock.common.message.ResponseMessage.*;
import static com.sillock.config.ApiDocumentUtils.getDocumentRequest;
import static com.sillock.config.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class SillogControllerTest extends AbstractControllerTest {

    @MockBean
    private SillogService sillogService;

    @MockBean
    private SillogMapper sillogMapper;

    @SillogUser
    @Test
    public void 실록_등록_테스트() throws Exception {
        SillogPostDto sillogPostDto = DtoFactory.sillogPostDto();
        sillogPostDto.setMemo(EntityFactory.basicMemoEntity());
        sillogPostDto.setQnaList(Arrays.asList(EntityFactory.basicQnaEntity()));

        String content = objectMapper.writeValueAsString(sillogPostDto);

        when(sillogService.registerSillog(any())).thenReturn(EntityFactory.basicObjectId());

        mockMvc.perform(post("/api/v1/sillogs")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(REGISTER_SILLOG)) // (5)
                .andDo(print())
                .andDo(document("api/v1/sillogs",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("title").description("제목"),
                                fieldWithPath("qnaList.[].question").description("QnA 질문 / 선택"),
                                fieldWithPath("qnaList.[].answer").description("QnA 답변 / 선택"),
                                fieldWithPath("memo.body").description("Memo / 선택"),
                                fieldWithPath("tagList.[].category").description("태그 카테고리"),
                                fieldWithPath("tagList.[].name").description("태그 이름"),
                                fieldWithPath("imageList.[]").description("이미지 리스트"),
                                fieldWithPath("fileList.[]").description("파일 리스트"),
                                fieldWithPath("dateList.[]").description("날짜 리스트")
                        ),
                        responseFields(
                                fieldWithPath("status").description("상태 값"),
                                fieldWithPath("message").description("결과 메시지"),
                                fieldWithPath("data").description("등록된 실록 ID"),
                                fieldWithPath("timestamp").description("타임 스탬프")
                        )
                ));
    }

    @SillogUser
    @Test
    public void 실록_수정_테스트() throws Exception {
        SillogPostDto sillogPostDto = DtoFactory.sillogPostDto();
        sillogPostDto.setMemo(EntityFactory.basicMemoEntity());
        sillogPostDto.setQnaList(Arrays.asList(EntityFactory.basicQnaEntity()));

        String content = objectMapper.writeValueAsString(sillogPostDto);

        given(sillogService.findById(any(ObjectId.class))).willReturn(EntityFactory.basicSillogMemoEntity());

        mockMvc.perform(patch("/api/v1/sillogs/{sillogId}", EntityFactory.basicObjectId())
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("message").value(UPDATE_SILLOG))
                .andDo(print())
                .andDo(document("api/v1/sillogs/sillogId",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("sillogId").description("실록 ID")
                        ),
                        requestFields(
                                fieldWithPath("title").description("제목"),
                                fieldWithPath("qnaList.[].question").description("QnA 질문 / 선택"),
                                fieldWithPath("qnaList.[].answer").description("QnA 답변 / 선택"),
                                fieldWithPath("memo.body").description("Memo / 선택"),
                                fieldWithPath("tagList.[].category").description("태그 카테고리"),
                                fieldWithPath("tagList.[].name").description("태그 이름"),
                                fieldWithPath("imageList.[]").description("이미지 리스트"),
                                fieldWithPath("fileList.[]").description("파일 리스트"),
                                fieldWithPath("dateList.[]").description("날짜 리스트")
                        ),
                        responseFields(
                                fieldWithPath("status").description("상태 값"),
                                fieldWithPath("message").description("결과 메시지"),
                                fieldWithPath("timestamp").description("타임 스탬프")
                        )
                ));
    }

    @SillogUser
    @Test
    public void 실록_삭제_테스트() throws Exception {
        given(sillogService.findById(any(ObjectId.class))).willReturn(EntityFactory.basicSillogMemoEntity());

        mockMvc.perform(delete("/api/v1/sillogs/{sillogId}", EntityFactory.basicObjectId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.jsonPath("message").value(DELETE_SILLOG))
                .andDo(print())
                .andDo(document("api/v1/sillogs/sillogId/delete",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("sillogId").description("실록 ID")
                        ),
                        responseFields(
                                fieldWithPath("status").description("상태 값"),
                                fieldWithPath("message").description("결과 메시지"),
                                fieldWithPath("timestamp").description("타임 스탬프")
                        )
                ));
    }

}
