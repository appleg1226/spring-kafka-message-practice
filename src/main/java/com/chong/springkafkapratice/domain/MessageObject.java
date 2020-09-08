package com.chong.springkafkapratice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MessageObject {

    private String messageName;
    private String content;
    private String producerName;

    private LocalDateTime creationTime;
}
