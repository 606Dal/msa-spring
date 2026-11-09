package com.example.spring.test.kafka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.JacksonJsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.mapping.DefaultJacksonJavaTypeMapper;
import org.springframework.kafka.support.mapping.JacksonJavaTypeMapper;

@Configuration
public class KafkaConsumerConfig {

    // String -> 역직렬화
    @Bean
    public RecordMessageConverter kafkaRecordMessageConverter() {
        JacksonJsonMessageConverter converter = new JacksonJsonMessageConverter();
        if (converter.getTypeMapper() instanceof DefaultJacksonJavaTypeMapper typeMapper) {
            typeMapper.setTypePrecedence(JacksonJavaTypeMapper.TypePrecedence.INFERRED);
        }
        return converter;
    }
}
