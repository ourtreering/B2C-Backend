package com.sillock.domain.sillog.service;

import com.sillock.domain.sillog.model.entity.Qna;
import com.sillock.domain.sillog.model.entity.Sillog;
import com.sillock.domain.sillog.repository.SillogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SillogService {
    private final SillogRepository sillogRepository;

    public List<Sillog> getSillogList(Long memberId){

        return sillogRepository.findAllById(memberId);
    }
}