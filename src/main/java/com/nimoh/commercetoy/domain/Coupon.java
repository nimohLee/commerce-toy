package com.nimoh.commercetoy.domain;

import com.nimoh.commercetoy.enums.CouponStatus;
import com.nimoh.commercetoy.enums.SaleMethod;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    //@Column(columnDefinition = "ENUM('RATE', 'PRICE')")
    private SaleMethod method;

    private double rate;

    private int price;

    private String description;

    @Enumerated(EnumType.STRING)
    //@Column(columnDefinition = "ENUM('USED', 'USABLE', 'EXPIRED')")
    private CouponStatus couponStatus;

    private LocalDateTime issueExpireDt;
}
