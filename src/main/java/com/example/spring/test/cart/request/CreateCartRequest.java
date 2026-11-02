package com.example.spring.test.cart.request;

import lombok.Getter;

@Getter
public class CreateCartRequest {

    private Long productId;
    private Long memberId;
}
