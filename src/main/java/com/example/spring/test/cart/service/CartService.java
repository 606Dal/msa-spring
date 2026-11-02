package com.example.spring.test.cart.service;

import com.example.spring.test.cart.entity.Cart;
import com.example.spring.test.cart.repository.CartRepository;
import com.example.spring.test.cart.request.CreateCartRequest;
import com.example.spring.test.common.exception.CustomExcption;
import com.example.spring.test.common.exception.ErrorCode;
import com.example.spring.test.member.entity.Member;
import com.example.spring.test.member.repository.MemberRepository;
import com.example.spring.test.product.entity.Product;
import com.example.spring.test.product.repository.ProductRepository;
import com.example.spring.test.product.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public void createCart(CreateCartRequest createCartRequest) {
        Product product = productRepository.findById(createCartRequest.getProductId())
                .orElseThrow(() -> new CustomExcption((ErrorCode.PRODUCT_NOT_FOUND)));


        Member member = memberRepository.findById(createCartRequest.getMemberId()).
                orElseThrow(() -> new CustomExcption((ErrorCode.MEMBER_NOT_FOUND)));

        Cart cart = Cart.builder()
                .product(product)
                .member(member)
                .build();

        cartRepository.save(cart);

    }

    @Transactional(readOnly = true)
    public List<ProductResponse> getCart(String memberUuid) {
//        List<Cart> carts = cartRepository.findAllByMemberId(memberId);

//        List<ProductResponse> result = new ArrayList<>();

//        for (Cart item : carts) {
////            ProductResponse productResponse = ProductResponse.from(item);
////            ProductResponse productResponse = ProductResponse.builder()
////                    .id(item.getProduct().getId())
////                    .price(item.getProduct().getPrice())
////                    .productName(item.getProduct().getName())
////                    .build();
//
//            result.add(productResponse);
//        }

        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new CustomExcption(ErrorCode.MEMBER_NOT_FOUND));

        return cartRepository.findAllByMemberId(member.getId()).stream().map(ProductResponse::from).toList();
    }
}
