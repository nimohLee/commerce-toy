package com.nimoh.commercetoy.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderStatus {

    SUCCESS("success"),
    FAIL("fail"),
    CANCEL("cancel");

    private final String status;
}
