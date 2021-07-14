package com.sillock.domain.sillog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sillock.common.AbstractControllerTest;
import com.sillock.domain.sillog.model.dto.QnaDto;
import com.sillock.domain.sillog.model.dto.SillogDto;
import com.sillock.domain.sillog.service.SillogService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;

import static com.sillock.common.message.ResponseMessage.REGISTER_SILLOG;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class SillogControllerTest extends AbstractControllerTest {
    @Autowired
    ObjectMapper objectMapper;

    @Mock
    private SillogService sillogService;


}
