package com.sillock.domain.sillog.repository;

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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@RunWith(SpringRunner.class)
//@DataMongoTest
@SpringBootTest
@MongoUnitTest
public class SillogRepositoryTest {

    @Autowired
    private SillogRepository sillogRepository;

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

}
