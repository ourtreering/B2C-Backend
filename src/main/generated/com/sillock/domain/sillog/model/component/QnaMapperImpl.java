package com.sillock.domain.sillog.model.component;

import com.sillock.domain.sillog.model.dto.QnaDto;
import com.sillock.domain.sillog.model.dto.QnaDto.QnaDtoBuilder;
import com.sillock.domain.sillog.model.entity.Qna;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-12T01:17:29+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.10 (Oracle Corporation)"
)
@Component
public class QnaMapperImpl implements QnaMapper {

    @Override
    public QnaDto toDto(Qna qna) {
        if ( qna == null ) {
            return null;
        }

        QnaDtoBuilder qnaDto = QnaDto.builder();

        qnaDto.question( qna.getQuestion() );
        qnaDto.answer( qna.getAnswer() );
        List<String> list = qna.getTags();
        if ( list != null ) {
            qnaDto.tags( new ArrayList<String>( list ) );
        }

        return qnaDto.build();
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
        if ( list != null ) {
            qna.setTags( new ArrayList<String>( list ) );
        }

        return qna;
    }
}
