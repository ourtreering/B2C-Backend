package com.sillock.domain.sillog.service;


import com.sillock.domain.sillog.model.component.QnaMapper;
import com.sillock.domain.sillog.model.dto.QnaDto;
import com.sillock.domain.sillog.model.dto.SillogBySequenceDto;
import com.sillock.domain.sillog.model.entity.Sillog;
import com.sillock.domain.sillog.repository.QnaRepository;
import com.sillock.domain.sillog.repository.SillogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class SillogService {
    private final SillogRepository sillogRepository;
    private final QnaMapper qnaMapper;
    private final QnaRepository qnaRepository;

    @Transactional(readOnly = true)
    public List<Sillog> getSillogList(Long memberId){
        return sillogRepository.findAllByMemberId(memberId);
    }

    @Transactional
    public void register(Sillog sillog){
        qnaRepository.saveAll(sillog.getQnaData());
        sillogRepository.save(sillog);
    }

    /* 나중에 실록 태그가 생겼을 때의 사용할 코드 */
//    @Transactional(readOnly = true)
//    public List<Sillog> findSillogList(Long memberId, String title, String tag){
//        if(title == null && tag == null) return  sillogRepository.findAllById(memberId);
//        else if(tag == null) return  sillogRepository.findByMemberIdAndTitle(memberId, title);
//        else if(title == null) return sillogRepository.findByMemberIdAndTag(memberId, tag);
//        return  sillogRepository.findByMemberIdAndTitle(memberId, title);
//    }

    /* 임시로 태그 없이 멤버id와 title만 사용하는 코드. tag생기면 삭제예정 */
    @Transactional(readOnly = true)
    public List<Sillog> findSillogList(Long memberId, String title){
        if(title == null) return sillogRepository.findAllByMemberId(memberId);
        else return sillogRepository.findByMemberIdAndTitle(memberId, title);
    }

    @Transactional(readOnly = true)
    public SillogBySequenceDto findSillogBySequence(Long memberId, String title, int sequence) {
        List<Sillog> sillogListByTitle = this.findSillogList(memberId,title);
        int maxSequence = sillogListByTitle.size();

        Sillog sillogBySequence = sillogRepository.findByMemberIdAndTitleAndSequence(memberId,title,sequence);
        List<QnaDto> qnaDto = sillogBySequence.getQnaData().stream().map(qnaMapper::toDto).collect(Collectors.toList());
        return SillogBySequenceDto.builder()
                .author(sillogBySequence.getAuthor())
                .title(sillogBySequence.getTitle())
                .sequence(sillogBySequence.getSequence())
                .maxSequence(maxSequence)
                .qnaData(qnaDto)
                .image(sillogBySequence.getImage())
                .qualification(sillogBySequence.getQualification())
                .regDate(sillogBySequence.getRegDate())
                .startDate(sillogBySequence.getStartDate())
                .endDate(sillogBySequence.getEndDate())
                .build();
    }
}