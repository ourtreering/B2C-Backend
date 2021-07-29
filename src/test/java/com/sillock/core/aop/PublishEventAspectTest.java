package com.sillock.core.aop;

import com.sillock.common.EntityFactory;
import com.sillock.domain.tag.repository.TagInfoRepository;
import com.sillock.domain.tag.repository.TagRepository;
import com.sillock.domain.tag.service.TagService;
import com.sillock.event.entity.CalculateTagEvent;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

@SpringBootTest
class PublishEventAspectTest {

    @Autowired
    private PublishEventAspect publishEventAspect;

    @Autowired
    private TagInfoRepository tagInfoRepository;

    @Autowired
    private TagRepository tagRepository;

    @Test
    public void aspectTest(){
        AspectJProxyFactory factory = new AspectJProxyFactory(new TagService(tagInfoRepository, tagRepository));
        factory.addAspect(publishEventAspect);
        TagService tagService1 = factory.getProxy();
        CalculateTagEvent event = tagService1.saveTagList(new ObjectId(EntityFactory.basicObjectId()), Arrays.asList(EntityFactory.basicTagEntity()));
    }

}