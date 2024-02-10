package com.nimoh.commercetoy.payment.domain;

import com.nimoh.commercetoy.base.domain.BaseEntity;
import com.nimoh.commercetoy.payment.enums.PaymentMethod;
import com.nimoh.commercetoy.payment.enums.PaymentStatus;
import com.nimoh.commercetoy.order.domain.Orders;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id", unique = true)
    private Orders orders;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    private int price;

}
