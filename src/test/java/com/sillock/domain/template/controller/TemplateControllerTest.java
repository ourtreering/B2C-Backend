package com.sillock.domain.template.controller;

import com.sillock.annotation.SillogUser;
import com.sillock.common.AbstractControllerTest;
import com.sillock.common.EntityFactory;
import com.sillock.core.auth.jwt.model.SocialProfile;
import com.sillock.domain.template.model.dto.TemplateDto;
import com.sillock.domain.template.model.entity.Template;
import com.sillock.domain.template.model.entity.TemplateCategory;
import com.sillock.domain.template.service.TemplateService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sillock.common.message.ResponseMessage.CREATED;
import static com.sillock.config.ApiDocumentUtils.getDocumentRequest;
import static com.sillock.config.ApiDocumentUtils.getDocumentResponse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class TemplateControllerTest extends AbstractControllerTest {
    @MockBean
    private TemplateService templateService;

    @SillogUser
    @Test
    void getTemplates() throws Exception {
        Map<TemplateCategory, List<Template>> templateListMap = new HashMap<>();
        templateListMap.put(TemplateCategory.PROJECT, Arrays.asList(EntityFactory.basicTemplateEntity()));

        given(templateService.getTemplates()).willReturn(templateListMap);
        mockMvc.perform(get("/api/v1/templates")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andDo(document("api/v1/templates",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        responseFields(
                                fieldWithPath("status").description("상태 값"),
                                fieldWithPath("message").description("응답 메시지"),
                                subsectionWithPath("data").description("data"),
                                fieldWithPath("timestamp").description("타임스탬프")
                        )
                ));
    }

    @SillogUser
    @Test
    void createTemplate() throws Exception {
        TemplateDto templateDto = TemplateDto.builder().category(TemplateCategory.PROJECT).qnaList(Arrays.asList(EntityFactory.basicQnaEntity())).build();
        String content = objectMapper.writeValueAsString(templateDto);

        mockMvc.perform(post("/api/v1/templates")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("message").value(CREATED))
                .andDo(print())
                .andDo(document("api/v1/templates/post",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("category").description("카테고리"),
                                fieldWithPath("qnaList.[].question").description("질문"),
                                fieldWithPath("qnaList.[].answer").description("답변")
                        ),
                        responseFields(
                                fieldWithPath("status").description("상태 값"),
                                fieldWithPath("message").description("응답 메시지"),
                                fieldWithPath("timestamp").description("타임스탬프")
                        )
                ));
    }
}