package com.rachana.chat.realtime_app.producer;

import com.rachana.chat.realtime_app.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

@EmbeddedKafka(brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
@SpringBootTest
public class ProducerIntegrationTest {

    @Autowired
    private Producer producer;

    @Test
    @DirtiesContext
    void shouldPublishMessage() throws ExecutionException, InterruptedException {
        Message message = new Message("sender", "content");
        ListenableFuture<SendResult<String, Message>> content = producer.publish(message);

        assertThat(content.get().getProducerRecord().topic()).isEqualTo("message-topic");
    }
}
