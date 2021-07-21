package com.sillock.domain.sillog.service;


import com.sillock.domain.sillog.model.entity.Sillog;
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

//    @Transactional(readOnly = true)
//    public List<Sillog> getSillogList( memberId){
//        return sillogRepository.findAllByMemberId(memberId);
//    }

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
    public List<Sillog> findSillogList(String memberId, String title){
        if(title == null) return sillogRepository.findAllByMemberId(memberId);
        else return sillogRepository.findByMemberIdAndTitle(memberId, title);
    }


}