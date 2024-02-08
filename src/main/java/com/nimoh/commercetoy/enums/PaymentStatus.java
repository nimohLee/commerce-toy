package com.nimoh.commercetoy.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentStatus {

    WAIT("wait"),
    SUCCESS("success"),
    FAIL("fail"),
    CANCEL("cancel")
    ;

    private final String status;
}
