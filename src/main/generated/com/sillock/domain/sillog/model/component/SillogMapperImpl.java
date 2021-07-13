package com.sillock.domain.sillog.model.component;

import com.sillock.domain.sillog.model.dto.QnaDto;
import com.sillock.domain.sillog.model.dto.SillogDto;
import com.sillock.domain.sillog.model.entity.Qna;
import com.sillock.domain.sillog.model.entity.Sillog;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-12T01:17:29+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.10 (Oracle Corporation)"
)
@Component
public class SillogMapperImpl implements SillogMapper {

    @Autowired
    private QnaMapper qnaMapper;

    @Override
    public SillogDto toDto(Sillog sillog) {
        if ( sillog == null ) {
            return null;
        }

        SillogDto sillogDto = new SillogDto();

        sillogDto.setAuthor( sillog.getAuthor() );
        sillogDto.setTitle( sillog.getTitle() );
        sillogDto.setSequence( sillog.getSequence() );
        sillogDto.setQnaData( qnaListToQnaDtoList( sillog.getQnaData() ) );
        List<String> list1 = sillog.getImage();
        if ( list1 != null ) {
            sillogDto.setImage( new ArrayList<String>( list1 ) );
        }
        List<String> list2 = sillog.getQualification();
        if ( list2 != null ) {
            sillogDto.setQualification( new ArrayList<String>( list2 ) );
        }
        sillogDto.setRegDate( sillog.getRegDate() );
        sillogDto.setStartDate( sillog.getStartDate() );
        sillogDto.setEndDate( sillog.getEndDate() );

        return sillogDto;
    }

    @Override
    public Sillog toEntity(SillogDto sillogDto) {
        if ( sillogDto == null ) {
            return null;
        }

        Sillog sillog = new Sillog();

        sillog.setAuthor( sillogDto.getAuthor() );
        sillog.setTitle( sillogDto.getTitle() );
        sillog.setSequence( sillogDto.getSequence() );
        sillog.setQnaData( qnaDtoListToQnaList( sillogDto.getQnaData() ) );
        List<String> list1 = sillogDto.getImage();
        if ( list1 != null ) {
            sillog.setImage( new ArrayList<String>( list1 ) );
        }
        List<String> list2 = sillogDto.getQualification();
        if ( list2 != null ) {
            sillog.setQualification( new ArrayList<String>( list2 ) );
        }
        sillog.setRegDate( sillogDto.getRegDate() );
        sillog.setStartDate( sillogDto.getStartDate() );
        sillog.setEndDate( sillogDto.getEndDate() );

        return sillog;
    }

    protected List<QnaDto> qnaListToQnaDtoList(List<Qna> list) {
        if ( list == null ) {
            return null;
        }

        List<QnaDto> list1 = new ArrayList<QnaDto>( list.size() );
        for ( Qna qna : list ) {
            list1.add( qnaMapper.toDto( qna ) );
        }

        return list1;
    }

    protected List<Qna> qnaDtoListToQnaList(List<QnaDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Qna> list1 = new ArrayList<Qna>( list.size() );
        for ( QnaDto qnaDto : list ) {
            list1.add( qnaMapper.toEntity( qnaDto ) );
        }

        return list1;
    }
}
