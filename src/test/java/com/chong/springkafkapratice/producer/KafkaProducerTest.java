package com.chong.springkafkapratice.producer;

import com.chong.springkafkapratice.domain.MessageObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class KafkaProducerTest {
    @Autowired
    KafkaProducer kafkaProducer;

    @Test
    void sendObjectMessageTest() throws InterruptedException {
        MessageObject object = MessageObject.builder()
                .messageName("test")
                .content("hello world")
                .producerName("kim")
                .creationTime(LocalDateTime.now())
                .build();

        ListenableFuture<SendResult<String, MessageObject>> futureReturn = kafkaProducer.sendObjectMessage("objectMessage", object, "0");

        Thread.sleep(5000);
        assertTrue(futureReturn.isDone());
    }

    @Test
    void sendStringMessageTest() throws InterruptedException {
        ListenableFuture<SendResult<String, String>> futureReturn = kafkaProducer.sendStringMessage("stringMessage", "hi i'm kafka");

        Thread.sleep(5000);
        assertTrue(futureReturn.isDone());
    }
}