package com.rachana.chat.realtime_app.producer;

import com.rachana.chat.realtime_app.model.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import static com.rachana.chat.realtime_app.constants.KafkaConfigConstants.MESSAGE_TOPIC;

@Component
public class Producer {

    private KafkaTemplate<String, Message> kafkaTemplate;

    public Producer(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public ListenableFuture<SendResult<String, Message>> publish(Message message) {
        return kafkaTemplate.send(MESSAGE_TOPIC, message);
    }
}