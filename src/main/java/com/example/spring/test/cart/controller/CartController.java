package com.example.spring.test.cart.controller;

import com.example.spring.test.cart.entity.Cart;
import com.example.spring.test.cart.request.CreateCartRequest;
import com.example.spring.test.cart.service.CartService;
import com.example.spring.test.product.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping
    public void createCart(@RequestBody CreateCartRequest createCartRequest) {
        cartService.createCart(createCartRequest);
    }

    @GetMapping
    public List<ProductResponse> getCart(@AuthenticationPrincipal UserDetails userDetails) {
        // return cartsercive에 getCart() 호출
//        List<ProductResponse> carts = cartService.getCart(userDetails.getUsername());
//        log.info("userDetails={}", userDetails.toString());

        return cartService.getCart(userDetails.getUsername());
    }
}
