package com.nimoh.commercetoy.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AddressStatus {
    MAIN("main"),
    SUB("sub");

    private final String status;
}
