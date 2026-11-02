package com.example.spring.test.product.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ProductRequest {

    @NotBlank(message = "상품명은 필수입니다.")
    @Size(max = 10, message = "상품은 최대 10자까지 가능합니다.")
    private String name;

    @NotNull(message = "가격은 필수입니다.")
    private Long price;
}
