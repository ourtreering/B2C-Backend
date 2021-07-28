package com.sillock.domain.sillog.service;


import com.sillock.domain.sillog.model.entity.Sillog;
import com.sillock.domain.sillog.model.entity.SillogTitle;
import com.sillock.domain.sillog.repository.SillogRepository;
import com.sillock.domain.tag.model.entity.Tag;
import com.sillock.domain.tag.repository.TagRepository;
import com.sillock.domain.tag.service.TagEventService;
import com.sillock.domain.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class SillogService {
    private final SillogRepository sillogRepository;
    private final TagRepository tagRepository;
    private final TagEventService tagEventService;

    @Transactional
    public void register(Sillog sillog){
        List<Tag> tagList = sillog.getTagList();
        tagRepository.saveAll(tagList);
        sillogRepository.save(sillog);
    }

    @Transactional(readOnly = true)
    public List<Sillog> getMemberSillogList(ObjectId memberId){
        return sillogRepository.findAllByMemberId(memberId);
    }

    @Transactional(readOnly = true)
    public List<SillogTitle> getMemberSillogTitleList(ObjectId memberId){
        List<SillogTitle> sillogTitleList = sillogRepository.findTitlesByMemberId(memberId)
                .stream()
                .collect(Collectors.groupingBy(SillogTitle::getTitle,
                        Collectors.maxBy(Comparator.comparing(SillogTitle::getRegDate))))
                .values()
                .stream()
                .map(Optional::get).collect(Collectors.toList());

        return sillogTitleList;
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
//    @Transactional(readOnly = true)
//    public List<Sillog> findSillogList(ObjectId memberId, String title){
//        if(title == null) return sillogRepository.findAllByAuthorId(memberId);
//        else return sillogRepository.findByAuthorIdAndTitle(memberId, title);
//    }


}