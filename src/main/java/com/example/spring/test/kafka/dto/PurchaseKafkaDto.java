package com.example.spring.test.kafka.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PurchaseKafkaDto {
    private long id;
    private long productId;
}
