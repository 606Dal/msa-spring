package com.example.spring.test.product.response;

import com.example.spring.test.cart.entity.Cart;
import com.example.spring.test.product.entity.Product;
import lombok.*;

@Getter
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String productName;
    private Long price;

    @Builder
    public ProductResponse(Long id, String productName, Long price) {
        this.id = id;
        this.productName = productName;
        this.price = price;
    }

    public static ProductResponse from(Cart cart) {
        return ProductResponse.builder()
                .id(cart.getProduct().getId())
                .productName(cart.getProduct().getName())
                .price(cart.getProduct().getPrice())
                .build();
    }

    public static ProductResponse from(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .productName(product.getName())
                .price(product.getPrice())
                .build();
    }
}
