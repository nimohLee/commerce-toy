package com.nimoh.commercetoy.user.domain;

import com.nimoh.commercetoy.base.domain.BaseEntity;
import com.nimoh.commercetoy.user.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Builder
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

    public User(String name) {
        this.name = name;
    }
}
