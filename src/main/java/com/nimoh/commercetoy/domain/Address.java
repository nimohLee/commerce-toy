package com.nimoh.commercetoy.domain;

import com.nimoh.commercetoy.enums.AddressStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    private String city;

    private String street;

    private String zipCode;

    @Enumerated(EnumType.STRING)
    private AddressStatus addressStatus;

}
