package com.sillock.domain.sillog.service;

import com.sillock.domain.sillog.model.component.QnaMapper;
import com.sillock.domain.sillog.model.dto.QnaDto;
import com.sillock.domain.sillog.model.dto.SillogBySequenceDto;
import com.sillock.domain.sillog.model.entity.Sillog;
import com.sillock.domain.sillog.repository.SillogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SillogService {
    private final SillogRepository sillogRepository;
    private final QnaMapper qnaMapper;

    public List<Sillog> getSillogList(String memberId){
        return sillogRepository.findAllById(memberId);
    }

    public List<Sillog> findSillogByTitle(String memberId, String title){
        return  sillogRepository.findByIdAndTitle(memberId, title);
    }
    public SillogBySequenceDto findSillogBySequence(String memberId, String title, int sequence) {
        List<Sillog> sillogListByTitle = this.findSillogByTitle(memberId,title);
        int maxSequence = sillogListByTitle.size();

        Sillog sillogBySequence = sillogRepository.findByIdAndTitleAndSequence(memberId,title,sequence);
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