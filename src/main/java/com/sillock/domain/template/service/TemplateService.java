package com.sillock.domain.template.service;

import com.sillock.common.message.ExceptionMessage;
import com.sillock.domain.template.model.dto.TemplateListResponse;
import com.sillock.domain.template.model.entity.Template;
import com.sillock.domain.template.model.entity.TemplateCategory;
import com.sillock.domain.template.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@RequiredArgsConstructor
@Service
public class TemplateService {
    private final TemplateRepository templateRepository;

    @Transactional(readOnly = true)
    public Map<TemplateCategory, List<Template>> getTemplates(){
        List<Template> templateList = templateRepository.findAll();
        Map<TemplateCategory, List<Template>> templateListMap = new HashMap<>();

        for(Template template : templateList){

            if(templateListMap.containsKey(template.getCategory())) {
                templateListMap.get(template.getCategory()).add(template);
            } else {
                templateListMap.put(template.getCategory(), new LinkedList<>(Arrays.asList(template)));
            }

        }

        return templateListMap;
    }

    @Transactional
    public void saveTemplate(Template template) {
        templateRepository.save(template);
    }

    @Transactional(readOnly = true)
    public Template findById(ObjectId id) {
        return templateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessage.TEMPLATE_ENTITY_NOT_FOUND));
    }

    @Transactional
    public void deleteTemplate(ObjectId id){
        templateRepository.deleteById(id);
    }

}
