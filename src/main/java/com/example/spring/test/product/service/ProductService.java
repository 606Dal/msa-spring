package com.example.spring.test.product.service;

import com.example.spring.test.common.exception.CustomExcption;
import com.example.spring.test.common.exception.ErrorCode;
import com.example.spring.test.product.request.PatchRequestProduct;
import com.example.spring.test.product.request.ProductRequest;
import com.example.spring.test.product.response.ProductResponse;
import com.example.spring.test.product.entity.Product;
import com.example.spring.test.product.repository.ProductRepository;
import com.example.spring.test.product.request.UpdateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Product> productList() {

//        List<Product> productList = productRepository.findAll();
        List<Product> productList = productRepository.findByDeletedFalse();

        return productList;
    }

    @Transactional
    public ProductResponse product(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomExcption(ErrorCode.PRODUCT_NOT_FOUND));

//        ProductResponse response = new ProductResponse();
//        response.setId(id);
//        response.setName(product.getName());
//        product.setPrice(100000L);

//        ProductResponse response = ProductResponse.builder()
//                .id(product.getId())
//                .productName(product.getName())
//                .price(product.getPrice())
//                .build();

        return ProductResponse.from(productRepository.findById(id).get());
    }

    @Transactional
    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
    }

    @Transactional
    public void updateProduct(UpdateProductRequest updateProductRequest, Long productId) {

        Product oldProduct = productRepository.findById(productId).get();

        Product newProduct = Product.builder()
                .id(oldProduct.getId()) // uuid가 오게 될 것
                .name(updateProductRequest.getName())
                .price(updateProductRequest.getPrice())
                .build();

        productRepository.save(newProduct);
    }

    @Transactional
    public void patchProduct(PatchRequestProduct patchRequestProduct, Long productId) {
        Product oldProduct = productRepository.findById(productId).get();

        oldProduct.changePriceByAdmin(patchRequestProduct.getPrice());
    }

    @Transactional
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Transactional
    public void softDeleteProduct(Long productId) {
        Product product = productRepository.findById(productId).get();
        product.softDeleteByUser();
    }
}
