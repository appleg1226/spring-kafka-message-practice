package com.chong.springkafkapratice.consumer;

import java.util.HashMap;
import java.util.Map;

import com.chong.springkafkapratice.domain.MessageObject;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;


@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    public ConsumerFactory<String, MessageObject> messageObjectConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
                new JsonDeserializer<>(MessageObject.class, false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MessageObject> messageObjectKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MessageObject> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(messageObjectConsumerFactory());
        return factory;
    }
}


