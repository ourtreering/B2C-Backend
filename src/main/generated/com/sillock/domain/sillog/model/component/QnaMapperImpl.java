package com.sillock.domain.sillog.model.component;

import com.sillock.domain.sillog.model.dto.QnaDto;
import com.sillock.domain.sillog.model.entity.Qna;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-14T10:11:52+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.10 (Oracle Corporation)"
)
@Component
public class QnaMapperImpl implements QnaMapper {

    @Override
    public QnaDto toDto(Qna qna) {
        if ( qna == null ) {
            return null;
        }

        QnaDto qnaDto = new QnaDto();

        qnaDto.setQuestion( qna.getQuestion() );
        qnaDto.setAnswer( qna.getAnswer() );

        return qnaDto;
    }

    @Override
    public Qna toEntity(QnaDto qnaDto) {
        if ( qnaDto == null ) {
            return null;
        }

        Qna qna = new Qna();

        qna.setQuestion( qnaDto.getQuestion() );
        qna.setAnswer( qnaDto.getAnswer() );
        List<String> list = qnaDto.getTags();

        return qna;
    }
}
