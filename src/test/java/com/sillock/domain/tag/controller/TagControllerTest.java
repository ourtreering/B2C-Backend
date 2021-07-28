package com.sillock.domain.tag.controller;

import com.sillock.common.AbstractControllerTest;
import com.sillock.common.EntityFactory;
import com.sillock.domain.sillog.model.entity.Sillog;
import com.sillock.domain.tag.model.entity.Tag;
import com.sillock.domain.tag.model.entity.TagInfo;
import com.sillock.domain.tag.model.mapper.TagMapper;
import com.sillock.domain.tag.service.TagService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.sillock.common.message.ResponseMessage.READ_SILLOG_LIST;
import static com.sillock.common.message.ResponseMessage.READ_TAG_INFO_LIST;
import static com.sillock.config.ApiDocumentUtils.getDocumentRequest;
import static com.sillock.config.ApiDocumentUtils.getDocumentResponse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class TagControllerTest extends AbstractControllerTest {

    @MockBean
    private TagService tagService;

    @Test
    void 기본_태그_리스트_조회() throws Exception {
        TagInfo tagInfo1 = EntityFactory.basicTagInfoEntity();
        TagInfo tagInfo2 = EntityFactory.basicTagInfoEntity();
        tagInfo2.setCategory("test category2");

        given(tagService.getDefaultTagList()).willReturn(Arrays.asList(tagInfo1, tagInfo2));

        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/tags/default")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(READ_TAG_INFO_LIST)) // (5)
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].category").value("test category"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].tagNameList[0]").value("tag1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].tagNameList[1]").value("tag2"))
                .andDo(print())
                .andDo(document("api/v1/tags/default",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        responseFields(
                                fieldWithPath("status").description("상태 값"),
                                fieldWithPath("message").description("결과 메시지"),
                                fieldWithPath("data.[].category").description("태그 카테고리"),
                                fieldWithPath("data.[].tagNameList.[]").description("태그 이름 리스트"),
//                                subsectionWithPath("data").description("data"),
                                fieldWithPath("timestamp").description("타임 스탬프")
                        )
                ));
    }
}