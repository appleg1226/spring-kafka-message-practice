# spring-kafka-message-practice

spring과 kafka client를 이용한 producer, consumer 코드 작성 연습 프로젝트 <br/>

Spring Kafka 통합 라이브러리를 이용하여 개발(Spring boot 2.3.3 기반)
```
dependencies {
	implementation 'org.springframework.kafka:spring-kafka'
}
```
## 프로젝트 구현 목적
- 카프카 프로듀서와 컨슈머를 스프링으로 추상화한 라이브러리를 직접 사용하여 기능 숙지
- 이후에 사용할 프로젝트에 참고할 client 사용 방법/팁 지속적으로 업데이트


## 메시지 전송에 사용한 오브젝트

```
public class MessageObject {
    private String messageName;
    private String content;
    private String producerName;
    private LocalDateTime creationTime;
}
```

## Producer 부분 구현

>- KafkaTopicConfig.java: KafkaAdmin을 bean으로 등록하고, NewTopic을 bean으로 등록하면 자동으로 브로커에 Topic을 등록해준다. 

>- KafkaProducerConfig.java: Kafka Producer에 대한 설정을 등록한다.

>- KafkaProducer.java: Kafka 메시지 전송을 비동기로 구현. MessageObject와 String 두 가지로 보낼 수 있도록 구현.

>- KafkaProducerTest.java: Kafka 메시지 전송 후, ListenableFuture를 반환받아 콜백 확인.

## Consumer 부분 구현

>- KafkaConsumerConfig.java: Kafka Consumer, Listener에 대한 설정을 등록한다.

>- KafkaConsumer.java: MessageObject와 String 두 가지를 다른 Topic이름으로 받을 수 있도록 구현. 
