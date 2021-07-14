package com.sillock.domain.member.controller;

import com.sillock.common.AbstractControllerTest;
import com.sillock.common.object.BuilderObjects;
import com.sillock.domain.sillog.repository.SillogRepository;
import com.sillock.domain.sillog.service.SillogService;
import org.junit.jupiter.api.Test;
import org.mongounit.MongoUnitTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * created by hyunwoo 21/06/22
 */
@MongoUnitTest
public class MemberControllerTest extends AbstractControllerTest {

    @MockBean
    private SillogService sillogService;

    @MockBean
    private SillogRepository sillogRepository;



}
