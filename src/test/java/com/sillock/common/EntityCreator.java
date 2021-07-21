package com.sillock.common;

import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.sillog.model.entity.Memo;
import com.sillock.domain.sillog.model.entity.Qna;
import com.sillock.domain.sillog.model.entity.Tag;
import com.sillock.domain.template.model.entity.Template;
import com.sillock.domain.sillog.model.entity.Sillog;
import org.bson.types.ObjectId;

import javax.persistence.TableGenerator;
import java.time.LocalDate;
import java.util.Arrays;

public class EntityCreator {

    public static Qna basicQnaEntity(){
        return Qna.builder()
                .question("첫번째 질문입니다.")
                .answer("첫번째 답변입니다.")
                .build();
    }

    public static Member basicMemberEntity(){
        return Member.builder()
                .memberId(new ObjectId("1"))
                .email("twst@gmail.com")
                .birth(LocalDate.of(2021,07,21))
                .gender("남")
                .nickName("Test")
                .password("1234")
                .profileImage("/test.jpg")
                .build();
    }

    public static Template basicTemplateEntity(){
        return Template.builder()
                .id(new ObjectId("1"))
                .qnaList(Arrays.asList(basicQnaEntity()))
                .build();
    }

    public static Sillog basicSillogQnaEntity(){
        return Sillog.builder()
                .id(new ObjectId("1"))
                .author(basicMemberEntity())
                .title("제목")
                .nextSillog(null)
                .previousSillog(null)
                .qnaList(Arrays.asList(basicQnaEntity()))
                .memo(null)
                .tagList(Arrays.asList(basicTagEntity()))
                .image(Arrays.asList("/src/image"))
                .regDate(LocalDate.of(2021, 7, 7))
                .modDate(LocalDate.of(2021, 7, 7))
                .build();
    }

    public static Sillog basicSillogMemoEntity(){
        return Sillog.builder()
                .id(new ObjectId("1"))
                .author(basicMemberEntity())
                .title("제목")
                .nextSillog(null)
                .previousSillog(null)
                .qnaList(null)
                .memo(basicMemoEntity())
                .tagList(Arrays.asList(basicTagEntity()))
                .image(Arrays.asList("/src/image"))
                .regDate(LocalDate.of(2021, 7, 7))
                .modDate(LocalDate.of(2021, 7, 7))
                .build();
    }

    public static Memo basicMemoEntity(){
        return Memo.builder()
                .body("memo data")
                .build();
    }

    public static Tag basicTagEntity(){
        return Tag.builder()
                .id(new ObjectId("1"))
                .category("교육")
                .name("AI")
                .build();
    }

}