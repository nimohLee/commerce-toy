package com.nimoh.commercetoy.coupon.enums;

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
