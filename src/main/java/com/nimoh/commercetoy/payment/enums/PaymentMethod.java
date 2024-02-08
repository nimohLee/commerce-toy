package com.nimoh.commercetoy.payment.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentMethod {

    CASH("cash"),
    CARD("card"),
    SMART_PAY("smart_pay")
    ;

    private final String method;
}
