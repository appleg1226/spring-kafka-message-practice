package com.chong.springkafkapratice.producer;

import com.chong.springkafkapratice.domain.MessageObject;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Service
@RequiredArgsConstructor
@Log
public class KafkaProducer {

    private final KafkaTemplate<String, MessageObject> messageObjectKafkaTemplate;
    private final KafkaTemplate<String, String> stringKafkaTemplate;

    public ListenableFuture<SendResult<String, MessageObject>> sendObjectMessage(String topicName, MessageObject message, String partiton){
        ListenableFuture<SendResult<String, MessageObject>> futureGet = messageObjectKafkaTemplate.send(topicName,
                Integer.valueOf(partiton), String.valueOf(partiton), message);

        futureGet.addCallback(new KafkaSendCallback<String, MessageObject>(){
            @Override
            public void onSuccess(SendResult<String, MessageObject> result) {
                log.info("Message " + result.getProducerRecord().value().getMessageName() + " is sent");
            }

            @Override
            public void onFailure(KafkaProducerException ex) {
                log.info("Message didn't sent due to : " + ex.getMessage());
            }
        });

        return futureGet;
    }

    public ListenableFuture<SendResult<String, String>> sendStringMessage(String topicName, String message){
        ListenableFuture<SendResult<String, String>> futureGet = stringKafkaTemplate.send(topicName, message);

        futureGet.addCallback(new KafkaSendCallback<String, String>(){
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Message " + result.getProducerRecord().value() + " is sent");
            }

            @Override
            public void onFailure(KafkaProducerException ex) {
                log.info("Message didn't sent due to : " + ex.getMessage());
            }
        });

        return futureGet;
    }
}