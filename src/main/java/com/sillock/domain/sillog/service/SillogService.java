package com.sillock.domain.sillog.service;


import com.sillock.common.message.ExceptionMessage;
import com.sillock.domain.sillog.model.entity.Sillog;
import com.sillock.domain.sillog.model.entity.SillogTitle;
import com.sillock.domain.sillog.repository.SillogMongo;
import com.sillock.domain.sillog.repository.SillogRepository;
import com.sillock.domain.tag.model.entity.Tag;
import com.sillock.domain.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class SillogService {
    private final SillogRepository sillogRepository;
    private final TagService tagService;
    private final SillogMongo sillogMongo;

    @Transactional
    public String registerSillog(Sillog sillog){
        tagService.countUpTagList(sillog.getMemberId(), sillog.getTagList());
        String sillogId = sillogRepository.save(sillog).getId().toString();
        return sillogId;
    }

    @Transactional
    public void updateSillog(Sillog sillog, List<Tag> preTagList){
        tagService.updateTagList(sillog.getMemberId(), sillog.getTagList(), preTagList);

        sillogRepository.save(sillog);
    }

    @Transactional
    public void deleteSillog(ObjectId memberId, Sillog sillog){
        tagService.countDownTagList(memberId, sillog.getTagList());

        // Todo : tag cascade 설정하기

        sillogRepository.delete(sillog);
    }

    @Transactional(readOnly = true)
    public List<Sillog> getMemberSillogList(ObjectId memberId, String title){

        if(null != title){
            return sillogRepository.findAllByMemberIdAndTitle(memberId, title);
        }

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

    @Transactional(readOnly = true)
    public Sillog findById(ObjectId sillogId){
        return sillogRepository.findById(sillogId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessage.SILLOG_ENTITY_NOT_FOUND));
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