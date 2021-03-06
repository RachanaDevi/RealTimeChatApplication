package com.rachana.chat.realtime_app.controller;

import com.rachana.chat.realtime_app.model.Message;
import com.rachana.chat.realtime_app.producer.Producer;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private Producer producer;

    public ChatController(Producer producer) {
        this.producer = producer;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/send", consumes = "application/json")
    public void sendMessage(@RequestBody Message message) {
        producer.publish(message);
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/group")
    public Message broadcastGroupMessage(@Payload Message message) {
        //sending this message to all the subscribers
        return message;
    }
}
