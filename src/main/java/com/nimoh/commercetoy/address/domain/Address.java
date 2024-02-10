package com.nimoh.commercetoy.address.domain;

import com.nimoh.commercetoy.base.domain.BaseEntity;
import com.nimoh.commercetoy.address.enums.AddressStatus;
import com.nimoh.commercetoy.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String city;

    private String street;

    private String zipCode;

    @Enumerated(EnumType.STRING)
    private AddressStatus addressStatus;


    @Builder
    public Address( String city, String street, String zipCode) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAddressStatus(AddressStatus addressStatus) {
        this.addressStatus = addressStatus;
    }

    public boolean isMainAddress() {
        return addressStatus == AddressStatus.MAIN;
    }
}
