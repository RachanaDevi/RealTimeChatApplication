package com.rachana.chat.realtime_app.controller;

import com.rachana.chat.realtime_app.Message;
import com.rachana.chat.realtime_app.producer.Producer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private Producer producer;

    public ChatController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/send", consumes = "application/json")
    public void sendMessage(@RequestBody Message message) {
        producer.publish(message);
    }
}
