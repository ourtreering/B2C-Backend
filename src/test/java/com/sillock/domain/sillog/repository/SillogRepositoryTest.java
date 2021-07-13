package com.sillock.domain.sillog.repository;

import com.sillock.common.object.BuilderObjects;
import com.sillock.domain.sillog.model.entity.Qna;
import com.sillock.domain.sillog.model.entity.Sillog;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mongounit.MongoUnitTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@RunWith(SpringRunner.class)
//@DataMongoTest
@SpringBootTest
@MongoUnitTest
public class SillogRepositoryTest {

    @Autowired
    private SillogRepository sillogRepository;

    BuilderObjects builderObjects = new BuilderObjects();

    @Test
    public void 입력(){
        Qna qna = Qna.builder().question("질문").answer("답변").build();
        Sillog sillog = Sillog.builder().author("author").qnaData(Arrays.asList(qna)).build();

        sillogRepository.save(sillog);

        Optional<Sillog> byId = sillogRepository.findById(sillog.getId());
        assertEquals(byId.get().getAuthor(), "author");
        assertEquals(byId.get().getQnaData().get(0).getAnswer(), "답변");
        System.out.println("Id: " + sillog.getId());
    }

    @Test
    public void 이름으로_조회(){
        Sillog sillog = builderObjects.basicSillog();
        Sillog sillog2 = builderObjects.customSillog("글쓴이","제목2",1);
        sillogRepository.save(sillog);
        sillogRepository.save(sillog2);

        List<Sillog> myList = sillogRepository.findByIdAndTitle(sillog2.getId(), sillog2.getTitle());
        assertEquals(myList.get(0).getAuthor(), "글쓴이");
        assertEquals(myList.get(0).getTitle(), "제목2");
    }

    @Test
    public void 시퀀스로_조회(){
        Sillog sillog = builderObjects.basicSillog();
        Sillog sillog2 = builderObjects.customSillog("글쓴이","제목",2);
        Sillog sillog3 = builderObjects.customSillog("글쓴이","제목",3);
        sillogRepository.save(sillog);
        sillogRepository.save(sillog2);
        sillogRepository.save(sillog3);

        Sillog sillogBySequence = sillogRepository.findByIdAndTitleAndSequence(sillog2.getId(), sillog2.getTitle(), sillog2.getSequence());
        assertEquals(sillogBySequence.getAuthor(), "글쓴이");
        assertEquals(sillogBySequence.getTitle(), "제목");
        assertEquals(sillogBySequence.getSequence(), 2);
    }

}
