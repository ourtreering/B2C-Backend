package com.sillock.domain.sillog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sillock.common.AbstractControllerTest;
import com.sillock.domain.member.controller.MemberController;
import com.sillock.domain.sillog.model.dto.QnaDto;
import com.sillock.domain.sillog.model.dto.SillogDto;
import com.sillock.domain.sillog.model.entity.Sillog;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


public class SillogControllerTest extends AbstractControllerTest {
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void 실록_등록_테스트() throws Exception {
        QnaDto qnaDto = QnaDto.builder().question("질문").answer("답변").tags(Arrays.asList("교육", "봉사")).build();
        SillogDto sillogDto = SillogDto.builder()
                .author("sillog")
                .title("제목")
                .sequence(1)
                .data(Arrays.asList(qnaDto))
                .image(Arrays.asList("/src/image"))
                .qualification(Arrays.asList("/src/qualification"))
                .startDate(LocalDate.of(2021,07,05))
                .endDate(LocalDate.of(2021, 07, 12))
                .build();

        String content = objectMapper.writeValueAsString(sillogDto);

        mockMvc.perform(post("/api/sillogs")
                .content(content)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("sillog")) // (5)
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("제목"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sequence").value(1))
                .andDo(print())
                .andDo(document("api/sillogs",
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
