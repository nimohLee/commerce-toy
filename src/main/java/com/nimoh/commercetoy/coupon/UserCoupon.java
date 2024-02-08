package com.nimoh.commercetoy.coupon;

import com.nimoh.commercetoy.base.domain.BaseEntity;
import com.nimoh.commercetoy.coupon.enums.UserCouponStatus;
import com.nimoh.commercetoy.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCoupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="coupon_id")
    private Coupon coupon;

    @Enumerated(EnumType.STRING)
    private UserCouponStatus userCouponStatus;

    private LocalDateTime expireDt;
}
