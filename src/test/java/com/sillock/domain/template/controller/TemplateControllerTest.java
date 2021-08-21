package com.sillock.domain.template.controller;

import com.sillock.annotation.SillogUser;
import com.sillock.common.AbstractControllerTest;
import com.sillock.common.EntityFactory;
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

import static com.sillock.config.ApiDocumentUtils.getDocumentRequest;
import static com.sillock.config.ApiDocumentUtils.getDocumentResponse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
}