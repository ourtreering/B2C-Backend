package com.sillock.domain.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sillock.annotation.SillogUser;
import com.sillock.common.AbstractControllerTest;
import com.sillock.common.DtoFactory;
import com.sillock.common.EntityFactory;
import com.sillock.common.message.ResponseMessage;
import com.sillock.domain.sillog.model.component.SillogMapper;
import com.sillock.domain.sillog.model.dto.SillogPostDto;
import com.sillock.domain.sillog.model.entity.Qna;
import com.sillock.domain.sillog.model.entity.Sillog;
import com.sillock.domain.sillog.model.entity.SillogTitle;
import com.sillock.domain.sillog.service.SillogService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.sillock.common.message.ResponseMessage.*;
import static com.sillock.config.ApiDocumentUtils.getDocumentRequest;
import static com.sillock.config.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class MemberSillogControllerTest extends AbstractControllerTest {

    @Autowired
    private SillogMapper sillogMapper;

    @MockBean
    private SillogService sillogService;

    @Test
    void ?????????_??????_?????????_??????() throws Exception {
        Sillog sillog = EntityFactory.basicSillogMemoEntity();
        sillog.setQnaList(Arrays.asList(EntityFactory.basicQnaEntity()));
        sillog.setDateList(Arrays.asList(LocalDate.of(2021,07,07)));
        List<Sillog> sillogList = Arrays.asList(sillog);

        given(sillogService.getMemberSillogList(eq(new ObjectId(EntityFactory.basicObjectId())), any(String.class))).willReturn(sillogList);

        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/members/{memberId}/sillogs", EntityFactory.basicObjectId()).param("title", "test")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(READ_SILLOG_LIST)) // (5)
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].title").value("??????"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].memo.body").value("memo data"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].qnaList[0].question").value("????????? ???????????????."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].qnaList[0].answer").value("????????? ???????????????."))
                .andDo(print())
                .andDo(document("api/v1/members/memberId/sillogs",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("memberId").description("????????? ID")
                        ),
                        requestParameters(
                                parameterWithName("title").description("?????? - ??????")
                        ),
                        responseFields(
                                fieldWithPath("status").description("?????? ???"),
                                fieldWithPath("message").description("?????? ?????????"),
                                fieldWithPath("data.[].id").description("?????? id"),
                                fieldWithPath("data.[].title").description("??????"),
                                fieldWithPath("data.[].qnaList.[].question").description("QnA ??????"),
                                fieldWithPath("data.[].qnaList.[].answer").description("QnA ??????"),
                                fieldWithPath("data.[].memo.body").description("Memo ?????????"),
                                fieldWithPath("data.[].tagList.[].category").description("?????? ????????????"),
                                fieldWithPath("data.[].tagList.[].name").description("?????? ??????"),
                                fieldWithPath("data.[].dateList.[]").description("?????? ?????????"),
//                                subsectionWithPath("data").description("data"),
                                fieldWithPath("timestamp").description("?????? ?????????")
                        )
                ));
    }

    @Test
    void ?????????_??????_?????????_?????????_??????() throws Exception{

        SillogTitle sillogTitle = EntityFactory.basicSillogTitleEntity();
        List<SillogTitle> sillogTitleList = Arrays.asList(sillogTitle);

        given(sillogService.getMemberSillogTitleList(eq(new ObjectId(EntityFactory.basicObjectId())))).willReturn(sillogTitleList);

        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/members/{memberId}/sillogs/title", EntityFactory.basicObjectId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(READ_SILLOG_TITLE_LIST)) // (5)
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].title").value("??????"))
                .andDo(print())
                .andDo(document("api/v1/members/memberId/sillogs/title",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("memberId").description("????????? ID")
                        ),
                        responseFields(
                                fieldWithPath("status").description("?????? ???"),
                                fieldWithPath("message").description("?????? ?????????"),
                                fieldWithPath("data.[].title").description("??????"),
                                fieldWithPath("data.[].regDate").description("?????? ?????? ??????"),
                                fieldWithPath("timestamp").description("?????? ?????????")
                        )
                ));
    }

    @SillogUser
    @Test
    public void ??????_?????????_??????_?????????() throws Exception {
        Sillog sillog = EntityFactory.basicSillogMemoEntity();
        sillog.setQnaList(Arrays.asList(EntityFactory.basicQnaEntity()));
        sillog.setDateList(Arrays.asList(LocalDate.of(2021, 8, 26)));

        given(sillogService.findById(any(ObjectId.class))).willReturn(sillog);

        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/members/{memberId}/sillogs/{sillogId}", EntityFactory.basicObjectId(), EntityFactory.basicObjectId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(READ_SILLOG_DETAIL))
                .andDo(print())
                .andDo(document("api/v1/members/memberId/sillogs/sillogId",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("memberId").description("????????? ID"),
                                parameterWithName("sillogId").description("?????? ID")
                        ),
                        responseFields(
                                fieldWithPath("status").description("?????? ???"),
                                fieldWithPath("message").description("?????? ?????????"),
                                fieldWithPath("data.id").description("?????? id"),
                                fieldWithPath("data.title").description("??????"),
                                fieldWithPath("data.qnaList.[].question").description("QnA ??????"),
                                fieldWithPath("data.qnaList.[].answer").description("QnA ??????"),
                                fieldWithPath("data.memo.body").description("Memo ?????????"),
                                fieldWithPath("data.tagList.[].category").description("?????? ????????????"),
                                fieldWithPath("data.tagList.[].name").description("?????? ??????"),
                                fieldWithPath("data.dateList.[]").description("?????? ?????????"),
                                fieldWithPath("data.imageList.[]").description("????????? ?????????"),
                                fieldWithPath("data.fileList.[]").description("?????? ?????????"),
                                fieldWithPath("timestamp").description("?????? ?????????")
                        )
                ));
    }

}
