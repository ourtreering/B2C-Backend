package com.sillock.domain.member.controller;

import com.sillock.common.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * created by hyunwoo 21/06/22
 */
public class MemberControllerTest extends AbstractControllerTest {

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
