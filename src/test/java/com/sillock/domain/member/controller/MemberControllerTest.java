package com.sillock.domain.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sillock.annotation.SillogUser;
import com.sillock.common.AbstractControllerTest;
import com.sillock.common.DtoFactory;
import com.sillock.common.EntityFactory;
import com.sillock.core.auth.jwt.model.SocialProfile;
import com.sillock.domain.member.model.component.MemberMapper;
import com.sillock.domain.member.model.dto.MemberProfile;
import com.sillock.domain.member.model.dto.MemberSignUp;
import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.member.service.MemberAuthService;
import com.sillock.domain.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.sillock.common.message.ResponseMessage.*;
import static com.sillock.config.ApiDocumentUtils.getDocumentRequest;
import static com.sillock.config.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class MemberControllerTest extends AbstractControllerTest {

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberAuthService memberAuthService;

    @Autowired
    private ObjectMapper objectMapper;

    @SillogUser
    @Test
    public void 멤버를_조회한다() throws Exception {
        mockMvc.perform(get("/api/v1/members/me")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.email").value("test@gmail.com"))
                .andDo(print())
                .andDo(document("api/v1/members/me",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        responseFields(
                                fieldWithPath("status").description("상태 값"),
                                fieldWithPath("message").description("응답 메시지"),
                                fieldWithPath("data.id").description("사용자 id"),
                                fieldWithPath("data.email").description("사용자 이메일"),
                                fieldWithPath("data.nickname").description("사용자 닉네임"),
                                fieldWithPath("data.profileImage").description("사용자 프로필 이미지 URL"),
                                fieldWithPath("data.identifier").description("사용자 identifier"),
                                fieldWithPath("timestamp").description("타임스탬프")
                        )
                ));
    }

    @Test
    public void 사용자_로그인_테스트() throws Exception {
        SocialProfile socialProfile = new SocialProfile("id", "test@gmail.com");
        String content = objectMapper.writeValueAsString(socialProfile);

        given(memberAuthService.login(any(String.class))).willReturn(EntityFactory.basicMemberEntity());

        mockMvc.perform(post("/api/v1/members/login")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(LOGIN_SUCCESS))
                .andDo(print())
                .andDo(document("api/v1/members/login",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("id").description("소셜 프로필 id"),
                                fieldWithPath("email").description("소셜 프로필 이메일")
                        ),
                        responseFields(
                                fieldWithPath("status").description("상태 값"),
                                fieldWithPath("message").description("응답 메시지"),
                                fieldWithPath("data.accessToken").description("사용자 액세스 토큰"),
                                fieldWithPath("data.refreshToken").description("사용자 리프레시 토큰"),
                                fieldWithPath("timestamp").description("타임스탬프")
                        )
                ));
    }

    @Test
    public void 사용자_회원가입_테스트() throws Exception {
        MemberSignUp memberSignUp = DtoFactory.memberSignUpDto();
        String content = objectMapper.writeValueAsString(memberSignUp);

        given(memberAuthService.signup(any(Member.class))).willReturn(EntityFactory.basicMemberEntity());

        mockMvc.perform(post("/api/v1/members/signup")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(SIGN_UP_SUCCESS))
                .andDo(print())
                .andDo(document("api/v1/members/signup",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("email").description("사용자 이메일"),
                                fieldWithPath("nickname").description("사용자 닉네임"),
                                fieldWithPath("birth").description("사용자 생일"),
                                fieldWithPath("password").description("사용자 비밀번호"),
                                fieldWithPath("profileImage").description("사용자 프로필 이미지 주소(default : null)"),
                                fieldWithPath("gender").description("사용자 성별")
                        ),
                        responseFields(
                                fieldWithPath("status").description("상태 값"),
                                fieldWithPath("message").description("응답 메시지"),
                                fieldWithPath("data.accessToken").description("사용자 액세스 토큰"),
                                fieldWithPath("data.refreshToken").description("사용자 리프레시 토큰"),
                                fieldWithPath("timestamp").description("타임스탬프")
                        )
                ));
    }

    @SillogUser
    @Test
    public void 사용자_프로필_수정_테스트() throws Exception {
        MemberProfile profile = new MemberProfile();
        profile.setNickname("changed nickname");

        String content = objectMapper.writeValueAsString(profile);

        mockMvc.perform(patch("/api/v1/members/me")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(UPDATE_MEMBER_PROFILE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.nickname").value(profile.getNickname()))
                .andDo(print())
                .andDo(document("api/v1/members/me/patch",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("id").description("사용자 id - 선택"),
                                fieldWithPath("email").description("사용자 이메일 - 선택"),
                                fieldWithPath("nickname").description("사용자 닉네임 - 선택"),
                                fieldWithPath("profileImage").description("사용자 프로필 이미지 URL - 선택"),
                                fieldWithPath("identifier").description("사용자 identifier - 선택")
                        ),
                        responseFields(
                                fieldWithPath("status").description("상태 값"),
                                fieldWithPath("message").description("응답 메시지"),
                                fieldWithPath("data.id").description("사용자 id"),
                                fieldWithPath("data.email").description("사용자 이메일"),
                                fieldWithPath("data.nickname").description("사용자 닉네임"),
                                fieldWithPath("data.profileImage").description("사용자 프로필 이미지 URL"),
                                fieldWithPath("data.identifier").description("사용자 identifier"),
                                fieldWithPath("timestamp").description("타임스탬프")
                        )
                ));
    }

//
//    @Test
//    public void 사용자_실록_조회_테스트() throws Exception{
//        mockMvc.perform(get("/api/members/{memberId}/sillogs", 1)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].author").value("sillog"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].title").value("제목"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].sequence").value(1))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].qnaData[0].question").value("첫번째 질문입니다."))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].qnaData[0].answer").value("첫번째 답변입니다."))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].qnaData[0].tags[0]").value("tag1"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].image").value("/src/image"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].qualification").value("/src/qualification"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].regDate").value("2021-07-07"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].startDate").value("2021-07-07"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].endDate").value("2021-07-08"))
//                .andDo(print())
//                .andDo(document("api/sillogList",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        responseFields(
//                                fieldWithPath("status").description("HttpStatus"),
//                                fieldWithPath("message").description("메세지"),
//                                fieldWithPath("data.[].memberId").description("유저아이디"),
//                                fieldWithPath("data.[].author").description("글쓴이"),
//                                fieldWithPath("data.[].title").description("제목"),
//                                fieldWithPath("data.[].sequence").description("처음 쓸 경우 1, 이어쓸 경우 시퀀스"),
//                                fieldWithPath("data.[].qnaData.[].question").description("QnA 질문"),
//                                fieldWithPath("data.[].qnaData.[].answer").description("QnA 답변"),
//                                fieldWithPath("data.[].qnaData.[].tags.[]").description("QnA 태그"),
//                                fieldWithPath("data.[].image.[]").description("이미지"),
//                                fieldWithPath("data.[].qualification.[]").description("증명서"),
//                                fieldWithPath("data.[].regDate").description("등록일"),
//                                fieldWithPath("data.[].startDate").description("시작일"),
//                                fieldWithPath("data.[].endDate").description("종료일"),
//                                fieldWithPath("timestamp").description("행사리스트 데이터 불러온 시각(에포크타임스탬프 형태)")
//                        )
//                ));
//    }
//
//    @Test
//    public void 사용자_실록_동일이름_행사조회_테스트() throws Exception {
//        mockMvc.perform(get("/api/members/{memberId}/sillogs",1,"제목")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].author").value("sillog"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].title").value("제목"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].sequence").value(1))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].image").value("/src/image"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].qualification").value("/src/qualification"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].author").value("sillog"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].title").value("제목"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].sequence").value(1))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].image").value("/src/image"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].qualification").value("/src/qualification"))
//                .andDo(print())
//                .andDo(document("api/sillogListByTitle",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        responseFields(
//                                fieldWithPath("status").description("HttpStatus"),
//                                fieldWithPath("message").description("메세지"),
//                                fieldWithPath("data.[].memberId").description("유저아이디"),
//                                fieldWithPath("data.[].author").description("글쓴이"),
//                                fieldWithPath("data.[].title").description("제목은 모두 동일"),
//                                fieldWithPath("data.[].sequence").description("처음 쓸 경우 1, 이어쓸 경우 시퀀스"),
//                                fieldWithPath("data.[].qnaData.[].question").description("QnA 질문"),
//                                fieldWithPath("data.[].qnaData.[].answer").description("QnA 답변"),
//                                fieldWithPath("data.[].qnaData.[].tags.[]").description("QnA 태그"),
//                                fieldWithPath("data.[].image.[]").description("이미지"),
//                                fieldWithPath("data.[].qualification.[]").description("증명서"),
//                                fieldWithPath("data.[].regDate").description("등록일"),
//                                fieldWithPath("data.[].startDate").description("시작일"),
//                                fieldWithPath("data.[].endDate").description("종료일"),
//                                fieldWithPath("timestamp").description("행사리스트 데이터 불러온 시각(에포크타임스탬프 형태)")
//                        )
//                ));
//    }
}