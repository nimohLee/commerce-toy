package com.nimoh.commercetoy.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdateRequestDto {

    private String name;
    private int sales;
    private int stock;
    private int price;
}
