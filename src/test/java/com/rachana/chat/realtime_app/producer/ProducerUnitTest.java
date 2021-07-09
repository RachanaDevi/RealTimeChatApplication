package com.rachana.chat.realtime_app.producer;

import com.rachana.chat.realtime_app.Message;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.kafka.core.KafkaTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ProducerUnitTest {

    // question, why do we need to need to use any(), any() thing?
    // try to put something inside any any and see how it works
    // the real question is that what is mockito actually doing
    @Test
    void shouldPublishAnEventWithGivenTopic() {
        KafkaTemplate<String, Message> kafkaTemplate = mock(KafkaTemplate.class);

        Producer producer = new Producer(kafkaTemplate);
        producer.publish(mock(Message.class));

        ArgumentCaptor<String> topicArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(kafkaTemplate).send(topicArgumentCaptor.capture(), any());

        assertThat(topicArgumentCaptor.getValue()).isEqualTo("message-topic");
    }

    @Test
    void shouldPublishEventWithGivenMessage() {
        KafkaTemplate<String, Message> kafkaTemplate = mock(KafkaTemplate.class);

        Producer producer = new Producer(kafkaTemplate);
        Message message = new Message("sender", "content");
        producer.publish(message);

        ArgumentCaptor<Message> messageArgumentCaptor = ArgumentCaptor.forClass(Message.class);
        verify(kafkaTemplate).send(any(), messageArgumentCaptor.capture());

        assertThat(messageArgumentCaptor.getValue()).isEqualTo(message);
    }
}