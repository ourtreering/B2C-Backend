package com.sillock.domain.member.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * created by hyunwoo 21/06/22
 */
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "build/generated-snippets")
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void 멤버를_조회한다() throws Exception {
        mockMvc.perform(get("/api/members/test")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.memberId").value(1L)) // (5)
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("treering"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("test@gmail.com"))
                .andDo(print())
                .andDo(document("member/test",
                responseFields(
                        fieldWithPath("memberId").description("member unique id"),
                        fieldWithPath("name").description("name"),
                        fieldWithPath("email").description("email"),
                        fieldWithPath("identifier").description("identifier"),
                        fieldWithPath("uniqueCode").description("uniqueCode")
                )
        ));
    }
}
