package com.nimoh.commercetoy.user.domain;

import com.nimoh.commercetoy.address.domain.Address;
import com.nimoh.commercetoy.address.enums.AddressStatus;
import com.nimoh.commercetoy.base.domain.BaseEntity;
import com.nimoh.commercetoy.user.enums.Gender;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String birth;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String loginId;

    private String password;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "user")
    private List<Address> addressList = new ArrayList<>();

    @Builder
    public User(String name, String birth, String email, String loginId, String password, String phone, Gender gender, List<Address> addressList) {
        this.name = name;
        this.birth = birth;
        this.email = email;
        this.loginId = loginId;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.addressList = addressList;
    }

    public void addAddress(Address address) {
        if (addressList.size() > 0) {
            address.setAddressStatus(AddressStatus.SUB);
        } else {
            address.setAddressStatus(AddressStatus.MAIN);
        }

        addressList.add(address);
        address.setUser(this);
    }

    public Address getMainAddress() {
        for (Address address : addressList) {
            if (address.isMainAddress()) {
                return address;
            }
        }
        return addressList.get(0);
    }
}
