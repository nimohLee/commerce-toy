package com.nimoh.commercetoy.cart.domain;

import com.nimoh.commercetoy.base.domain.BaseEntity;
import com.nimoh.commercetoy.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", unique = true)
    private User user;

    private int totalPrice;

}
