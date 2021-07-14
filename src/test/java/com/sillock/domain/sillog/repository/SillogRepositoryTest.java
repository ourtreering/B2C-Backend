package com.sillock.domain.sillog.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


//@RunWith(SpringRunner.class)
//@DataMongoTest
@SpringBootTest
public class SillogRepositoryTest {

    @Autowired
    private SillogRepository sillogRepository;

    @Autowired
    private QnaRepository qnaRepository;


}
