package com.sillock.domain.tag.controller;

import com.sillock.annotation.SillogUser;
import com.sillock.common.AbstractControllerTest;
import com.sillock.common.EntityFactory;
import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.tag.model.entity.TagInfo;
import com.sillock.domain.tag.service.TagService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.sillock.common.message.ResponseMessage.READ_DEFAULT_TAG_INFO_LIST;
import static com.sillock.common.message.ResponseMessage.READ_MEMBER_TAG_INFO_LIST;
import static com.sillock.config.ApiDocumentUtils.getDocumentRequest;
import static com.sillock.config.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(READ_DEFAULT_TAG_INFO_LIST)) // (5)
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

    @SillogUser
    @Test
    void 사용자가_사용한_태그_리스트_조회() throws Exception {
        Member member = EntityFactory.basicMemberEntity();
        Map<String, Integer> subMap = new HashMap<>(){
            {
                put("tag1", 1);
                put("tag2", 1);
            }
        };
        Map<String, Map<String, Integer>> tagInfoMap = new HashMap<>(){
            {
                put("test category", subMap);
            }
        };

        given(tagService.getMemberTagInfo(any())).willReturn(tagInfoMap);

        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/tags/me")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(READ_MEMBER_TAG_INFO_LIST)) // (5)
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].category").value("test category"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].tagNameList[0]").value("tag1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].tagNameList[1]").value("tag2"))
                .andDo(print())
                .andDo(document("api/v1/tags/me",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        responseFields(
                                fieldWithPath("status").description("상태 값"),
                                fieldWithPath("message").description("결과 메시지"),
                                fieldWithPath("data.[].category").description("태그 카테고리"),
                                fieldWithPath("data.[].tagNameList.[]").description("태그 이름 리스트"),
                                fieldWithPath("timestamp").description("타임 스탬프")
                        )
                ));
    }
}