package com.sillock.domain.member.controller;

import com.sillock.common.AbstractControllerTest;
import com.sillock.common.object.BuilderObjects;
import com.sillock.domain.sillog.model.entity.Sillog;
import com.sillock.domain.sillog.repository.SillogRepository;
import com.sillock.domain.sillog.service.SillogService;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import org.mongounit.MongoUnitTest;
import org.mongounit.SeedWithDataset;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * created by hyunwoo 21/06/22
 */
@MongoUnitTest
public class MemberControllerTest extends AbstractControllerTest {

    @MockBean
    private SillogService sillogService;

    @MockBean
    private SillogRepository sillogRepository;

    BuilderObjects builderObjects = new BuilderObjects();

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
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("memberId").description("member unique id"),
                                fieldWithPath("name").description("name"),
                                fieldWithPath("email").description("email"),
                                fieldWithPath("identifier").description("identifier"),
                                fieldWithPath("uniqueCode").description("uniqueCode")
                        )
                ));
    }

    @Test
    public void 사용자_실록_조회_테스트() throws Exception{
        mockMvc.perform(get("/api/members/{memberId}/sillogs", 1, null)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].author").value("글쓴이"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].title").value("제목"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].sequence").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].qnaData[0].question").value("첫번째 질문입니다."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].qnaData[0].answer").value("첫번째 답변입니다."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].qnaData[0].tags[0]").value("tag1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].image").value("/src/image"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].qualification").value("/src/qualification"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].regDate").value("2021-07-07"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].startDate").value("2021-07-07"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].endDate").value("2021-07-08"))
                .andDo(print())
                .andDo(document("api/sillogList",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("status").description("HttpStatus"),
                                fieldWithPath("message").description("메세지"),
                                fieldWithPath("data.[].memberId").description("유저아이디"),
                                fieldWithPath("data.[].author").description("글쓴이"),
                                fieldWithPath("data.[].title").description("제목"),
                                fieldWithPath("data.[].sequence").description("처음 쓸 경우 1, 이어쓸 경우 시퀀스"),
                                fieldWithPath("data.[].qnaData.[].question").description("QnA 질문"),
                                fieldWithPath("data.[].qnaData.[].answer").description("QnA 답변"),
                                fieldWithPath("data.[].qnaData.[].tags.[]").description("QnA 태그"),
                                fieldWithPath("data.[].image.[]").description("이미지"),
                                fieldWithPath("data.[].qualification.[]").description("증명서"),
                                fieldWithPath("data.[].regDate").description("등록일"),
                                fieldWithPath("data.[].startDate").description("시작일"),
                                fieldWithPath("data.[].endDate").description("종료일"),
                                fieldWithPath("timestamp").description("행사리스트 데이터 불러온 시각(에포크타임스탬프 형태)")
                        )
                ));
    }

    @Test
    public void 사용자_실록_동일이름_행사조회_테스트() throws Exception {
        mockMvc.perform(get("/api/members/{memberId}/sillogs", 1,"title")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].author").value("글쓴이"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].title").value("제목2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].sequence").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].image").value("/src/image"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].qualification").value("/src/qualification"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].author").value("글쓴이"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].title").value("제목2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].sequence").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].image").value("/src/image"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].qualification").value("/src/qualification"))
                .andDo(print())
                .andDo(document("api/sillogListByTitle",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("status").description("HttpStatus"),
                                fieldWithPath("message").description("메세지"),
                                fieldWithPath("data.[].author").description("글쓴이"),
                                fieldWithPath("data.[].title").description("제목은 모두 동일"),
                                fieldWithPath("data.[].sequence").description("처음 쓸 경우 1, 이어쓸 경우 시퀀스"),
                                fieldWithPath("data.[].qnaData.[].question").description("QnA 질문"),
                                fieldWithPath("data.[].qnaData.[].answer").description("QnA 답변"),
                                fieldWithPath("data.[].qnaData.[].tags.[]").description("QnA 태그"),
                                fieldWithPath("data.[].image.[]").description("이미지"),
                                fieldWithPath("data.[].qualification.[]").description("증명서"),
                                fieldWithPath("data.[].regDate").description("등록일"),
                                fieldWithPath("data.[].startDate").description("시작일"),
                                fieldWithPath("data.[].endDate").description("종료일"),
                                fieldWithPath("timestamp").description("행사리스트 데이터 불러온 시각(에포크타임스탬프 형태)")
                        )
                ));
    }
}