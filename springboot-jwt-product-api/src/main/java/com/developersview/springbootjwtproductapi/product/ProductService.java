package com.developersview.springbootjwtproductapi.product;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {
    List<Product> productList = null;

    @PostConstruct
    public void loadProducts() {
        productList = IntStream.rangeClosed(1, 20)
                .mapToObj(i -> Product.builder()
                        .productId(i)
                        .name("Product " + i)
                        .quantity(new Random().nextInt(20))
                        .price(new Random().nextInt(5000)).build())
                .collect(Collectors.toList());
    }

    public List<Product> getProducts() {
        return productList;
    }

    public Product getProductByID(int id) {
        return productList.stream()
                .filter(product -> product.getProductId() == id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("Product with id " + id + " not found!"));
    }
}
