package com.nimoh.commercetoy.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserCouponStatus {

    USED("used"),
    USABLE("usable"),
    EXPIRED("expired");

    private final String status;
}
