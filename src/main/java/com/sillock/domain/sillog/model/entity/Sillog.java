package com.sillock.domain.sillog.model.entity;

import com.sillock.domain.tag.model.entity.Tag;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

/**
 * Todo
 * 1. Id에 Auto Increment 를 적용해야하나?
 * 2. sillog 에 대한 tag 설정
 * 3. qna list property 이름
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Document(collection = "sillog")
public class Sillog {
    @Id
    private ObjectId id;

    @Indexed
    private ObjectId memberId;

    private String title;

    private List<Qna> qnaList;

    private Memo memo;

    @DBRef
    private List<Tag> tagList;

    private List<String> imageList;

    private List<String> fileList;

    private List<LocalDate> dateList;

    @Builder.Default
    private LocalDate regDate = LocalDate.now();

    @Builder.Default
    private LocalDate modDate = LocalDate.now();

    public boolean isWriter(ObjectId memberId){
        return this.memberId.equals(memberId);
    }

}
