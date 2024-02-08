package com.nimoh.commercetoy.coupon.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CouponStatus {
    ISSUABLE("issuable"),
    EXPIRED("expired");
    private final String status;
}
