package com.nimoh.commercetoy.order.domain;

import com.nimoh.commercetoy.address.domain.Address;
import com.nimoh.commercetoy.base.domain.BaseEntity;
import com.nimoh.commercetoy.product.domain.Product;
import com.nimoh.commercetoy.order.enums.OrderStatus;
import com.nimoh.commercetoy.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private int totalPrice;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="address_id", unique = true)
    private Address address;
}
