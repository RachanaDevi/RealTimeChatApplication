package com.rachana.chat.realtime_app.producer;

import com.rachana.chat.realtime_app.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    private KafkaTemplate<String, Message> kafkaTemplate;

    public Producer(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(Message message) {
        kafkaTemplate.send("message-topic", message);
    }
}