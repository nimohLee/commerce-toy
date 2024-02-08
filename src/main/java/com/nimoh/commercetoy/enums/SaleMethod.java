package com.nimoh.commercetoy.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SaleMethod {

    RATE("rate"),
    PRICE("price");

    private final String method;
}
