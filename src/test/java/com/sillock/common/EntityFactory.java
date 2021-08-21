package com.sillock.common;

import com.sillock.domain.member.model.entity.Member;
import com.sillock.domain.sillog.model.entity.*;
import com.sillock.domain.tag.model.entity.MemberTagInfo;
import com.sillock.domain.tag.model.entity.Tag;
import com.sillock.domain.tag.model.entity.TagInfo;
import com.sillock.domain.template.model.entity.Template;
import com.sillock.domain.template.model.entity.TemplateCategory;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;

public class EntityFactory {

    public static Qna basicQnaEntity(){
        return Qna.builder()
                .question("첫번째 질문입니다.")
                .answer("첫번째 답변입니다.")
                .build();
    }

    public static Memo basicMemoEntity(){
        return Memo.builder()
                .body("memo data")
                .build();
    }

    public static String basicObjectId(){
        String result = "";

        for(int i  = 0 ; i < 23; i++) result += "0";

        return result + "1";
    }

    public static Member basicMemberEntity(){
        return Member.builder()
                .id(new ObjectId(basicObjectId()))
                .identifier("identifier")
                .email("test@gmail.com")
                .birth(LocalDate.of(2021,07,21))
                .gender("male")
                .nickname("test")
                .password("1234")
                .profileImage("/test.jpg")
                .build();
    }

    public static Template basicTemplateEntity(){
        return Template.builder()
                .category(TemplateCategory.PROJECT)
                .qnaList(Arrays.asList(basicQnaEntity()))
                .build();
    }

    public static Sillog basicSillogQnaEntity(){
        return Sillog.builder()
                .memberId(basicMemberEntity().getId())
                .title("제목")
                .qnaList(Arrays.asList(basicQnaEntity()))
                .memo(null)
                .tagList(Arrays.asList(basicTagEntity()))
                .imageList(Arrays.asList("/src/image"))
                .regDate(LocalDate.of(2021, 7, 7))
                .modDate(LocalDate.of(2021, 7, 7))
                .build();
    }

    public static Sillog basicSillogMemoEntity(){
        return Sillog.builder()
                .memberId(basicMemberEntity().getId())
                .title("제목")
                .qnaList(null)
                .memo(basicMemoEntity())
                .tagList(Arrays.asList(basicTagEntity()))
                .imageList(Arrays.asList("/src/image"))
                .fileList(Arrays.asList("/src/file"))
                .regDate(LocalDate.of(2021, 7, 7))
                .modDate(LocalDate.of(2021, 7, 7))
                .build();
    }

    public static Tag basicTagEntity(){
        return Tag.builder()
                .category("교육")
                .name("AI")
                .build();
    }

    public static SillogTitle basicSillogTitleEntity(){
        return new SillogTitle() {
            @Override
            public String getTitle() {
                return "제목";
            }

            @Override
            public LocalDate getRegDate() {
                return LocalDate.of(2021, 07, 07);
            }
        };
    }

    public static TagInfo basicTagInfoEntity(){
        return TagInfo.builder()
                .category("test category")
                .tagNameList(Arrays.asList("tag1", "tag2"))
                .build();
    }

    public static MemberTagInfo basicMemberTagInfoEntity(){
        return MemberTagInfo.builder()
                .memberId(new ObjectId(basicObjectId()))
                .tagInfoUsed(new HashMap<>())
                .build();
    }

}