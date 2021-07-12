package com.sillock.domain.sillog.service;

import com.sillock.domain.sillog.model.entity.Sillog;
import com.sillock.domain.sillog.repository.QnaRepository;
import com.sillock.domain.sillog.repository.SillogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class SillogService {
    private final SillogRepository sillogRepository;
    private final QnaRepository qnaRepository;

    @Transactional(readOnly = true)
    public List<Sillog> getSillogList(Long memberId){

        return sillogRepository.findAllById(memberId);
    }

    @Transactional
    public void register(Sillog sillog){
        qnaRepository.saveAll(sillog.getQnaData());
        sillogRepository.save(sillog);
    }
}