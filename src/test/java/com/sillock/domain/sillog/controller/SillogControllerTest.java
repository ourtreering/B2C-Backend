package com.sillock.domain.sillog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sillock.common.AbstractControllerTest;
import com.sillock.domain.sillog.model.dto.QnaDto;
import com.sillock.domain.sillog.model.dto.SillogDto;
import com.sillock.domain.sillog.model.entity.Qna;
import com.sillock.domain.sillog.model.entity.Sillog;
import com.sillock.domain.sillog.service.SillogService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;

import static com.sillock.common.message.ResponseMessage.REGISTER_SILLOG;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class SillogControllerTest extends AbstractControllerTest {
    @Autowired
    ObjectMapper objectMapper;

    @Mock
    private SillogService sillogService;

    @Test
    public void 실록_등록_테스트() throws Exception {
        QnaDto qnaDto = QnaDto.builder().question("질문").answer("답변").tags(Arrays.asList("교육", "봉사")).build();
        SillogDto sillogDto = SillogDto.builder()
                .author("sillog")
                .title("제목")
                .sequence(1)
                .qnaData(Arrays.asList(qnaDto))
                .image(Arrays.asList("/src/image"))
                .qualification(Arrays.asList("/src/qualification"))
                .regDate(LocalDate.of(2021,07,05))
                .startDate(LocalDate.of(2021,07,05))
                .endDate(LocalDate.of(2021, 07, 12))
                .build();

        String content = objectMapper.writeValueAsString(sillogDto);

        mockMvc.perform(post("/api/sillogs")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(REGISTER_SILLOG)) // (5)
                .andDo(print())
                .andDo(document("api/sillogs",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("author").description("글쓴이"),
                                fieldWithPath("title").description("제목"),
                                fieldWithPath("sequence").description("처음 쓸 경우 1, 이어쓸 경우 시퀀스"),
                                fieldWithPath("qnaData.[].question").description("QnA 질문"),
                                fieldWithPath("qnaData.[].answer").description("QnA 답변"),
                                fieldWithPath("qnaData.[].tags.[]").description("QnA 태그"),
                                fieldWithPath("image.[]").description("이미지"),
                                fieldWithPath("qualification.[]").description("증명서"),
                                fieldWithPath("regDate").description("등록일"),
                                fieldWithPath("startDate").description("시작일"),
                                fieldWithPath("endDate").description("종료일")
                        )
                ));
    }
}
