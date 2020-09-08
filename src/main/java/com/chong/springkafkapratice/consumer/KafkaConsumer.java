package com.chong.springkafkapratice.consumer;

import com.chong.springkafkapratice.domain.MessageObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    @KafkaListener(topicPartitions = @TopicPartition(topic = "objectMessage", partitions = "${kafka.id}"), groupId = "group1", containerFactory = "messageObjectKafkaListenerContainerFactory")
    public void objectMessageListener(MessageObject messageObject) {
        log.info("message is consumed");
        log.info(messageObject.toString());
    }

    @KafkaListener(topics = "stringMessage", groupId = "group2")
    public void stringMessageListener(String message) {
        log.info("message is consumed");
        log.info(message);
    }
}