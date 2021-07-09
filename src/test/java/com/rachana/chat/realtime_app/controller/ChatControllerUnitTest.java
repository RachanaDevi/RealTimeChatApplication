package com.rachana.chat.realtime_app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rachana.chat.realtime_app.Message;
import com.rachana.chat.realtime_app.producer.Producer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ChatController.class)
class ChatControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Producer producer;

    @Test
    void shouldSendMessage() throws Exception {
        var message = new Message("rachana", "hello world");
        var payload = new ObjectMapper().writeValueAsString(message);

        mockMvc.perform(post("/send")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andReturn();
    }
}
