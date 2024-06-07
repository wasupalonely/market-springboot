package com.springcourse.market.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Product {
    private Long productId;
    private String name;
    private Long categoryId;
    private BigDecimal price;
    private int stock;
    private boolean active;
    private Category category;
}
