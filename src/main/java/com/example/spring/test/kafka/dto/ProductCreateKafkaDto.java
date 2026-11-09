package com.example.spring.test.kafka.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductCreateKafkaDto {
    private Long productId;
    private String name;
    private Long price;

}
