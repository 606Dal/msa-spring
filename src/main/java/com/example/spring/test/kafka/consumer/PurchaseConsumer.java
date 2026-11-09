package com.example.spring.test.kafka.consumer;

import com.example.spring.test.kafka.dto.PurchaseKafkaDto;
import com.example.spring.test.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PurchaseConsumer {

    private final ProductService productService;

    @KafkaListener(topics = "purchase_success", groupId = "purchase_group")
    public void descreaseQty(PurchaseKafkaDto purchaseKafkaDto) {
        log.info("topic : {}", purchaseKafkaDto.toString());

        // product 서비스에서 descreaseQty 호출
        productService.decreaseQty(purchaseKafkaDto.getProductId());
    }
}
