package com.sillock.domain.sillog.repository;

import com.sillock.common.object.BuilderObjects;
import com.sillock.domain.sillog.model.entity.Qna;
import com.sillock.domain.sillog.model.entity.Sillog;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mongounit.AssertMatchesDataset;
import org.mongounit.LocationType;
import org.mongounit.MongoUnitTest;
import org.mongounit.SeedWithDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
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

    @Autowired
    private QnaRepository qnaRepository;

    BuilderObjects builderObjects = new BuilderObjects();


    @Test
    @SeedWithDataset("sillogRepositoryTest-seed.json")
    public void Author로_실록_조회() {

        List<Sillog> sillogList = sillogRepository.findSillogsByAuthor("sillog");
        assertEquals(1, sillogList.size());
        assertEquals("60ebdd48a275056ffc4e7a3c", sillogList.get(0).getId());
    }

    @Test
    @SeedWithDataset("sillogRepositoryTest-seed.json")
    @AssertMatchesDataset("sillogRepositoryTest-expected.json")
    public void Sillog_등록(){
        Qna qna1 = Qna.builder().question("question").answer("answer").tags(Arrays.asList("study", "volunteer")).build();
        Qna qna2 = Qna.builder().question("question2").answer("answer2").tags(Arrays.asList("study2", "volunteer2")).build();

        Sillog sillog = Sillog.builder()
                .author("author")
                .title("title2")
                .sequence(1)
                .qnaData(Arrays.asList(qna1, qna2))
                .image(Arrays.asList("/src/image"))
                .qualification(Arrays.asList("/src/qualification"))
                .regDate(LocalDate.of(2021, 7, 5))
                .startDate(LocalDate.of(2021, 7, 5))
                .endDate(LocalDate.of(2021, 7, 12))
                .build();

        qnaRepository.saveAll(Arrays.asList(qna1, qna2));
        sillogRepository.save(sillog);
    }

}
