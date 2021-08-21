package com.sillock.domain.template.service;

import com.sillock.domain.template.model.dto.TemplateListResponse;
import com.sillock.domain.template.model.entity.Template;
import com.sillock.domain.template.model.entity.TemplateCategory;
import com.sillock.domain.template.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
