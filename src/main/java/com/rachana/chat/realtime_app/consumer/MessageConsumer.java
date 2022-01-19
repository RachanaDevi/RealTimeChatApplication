package com.rachana.chat.realtime_app.consumer;

import com.rachana.chat.realtime_app.constants.KafkaConfigConstants;
import com.rachana.chat.realtime_app.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private final Logger logger = LoggerFactory.getLogger(MessageConsumer.class.getName());

    private final SimpMessagingTemplate simpMessagingTemplate;

    public MessageConsumer(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @KafkaListener(topics = KafkaConfigConstants.MESSAGE_TOPIC,
            groupId = KafkaConfigConstants.MESSAGE_CONSUMER_GROUP
    )
    public void consume(Message message) {
        logger.info("Consuming Message.......");
        simpMessagingTemplate.convertAndSend("/topic/group", message);
    }
}
