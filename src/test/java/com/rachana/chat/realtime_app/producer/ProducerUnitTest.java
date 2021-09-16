package com.rachana.chat.realtime_app.producer;

import com.rachana.chat.realtime_app.Message;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ProducerUnitTest {

    @Test
    void shouldPublishAnEventWithGivenTopic() {
        KafkaTemplate<String, Message> kafkaTemplate = mock(KafkaTemplate.class);

        Producer producer = new Producer(kafkaTemplate);
        producer.publish(new Message("sender","message"));

        ArgumentCaptor<String> topicArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(kafkaTemplate).send(topicArgumentCaptor.capture(), any());

        assertThat(topicArgumentCaptor.getValue()).isEqualTo("message-topic");
    }

    @Test
    void shouldPublishEventWithGivenMessage() {
        KafkaTemplate<String, Message> kafkaTemplate = mock(KafkaTemplate.class);

        Producer producer = new Producer(kafkaTemplate);
        Message message = new Message("sender", "message");
        producer.publish(message);

        ArgumentCaptor<Message> messageArgumentCaptor = ArgumentCaptor.forClass(Message.class);
        verify(kafkaTemplate).send(any(), messageArgumentCaptor.capture());

        assertThat(messageArgumentCaptor.getValue()).isEqualTo(message);
    }
}
