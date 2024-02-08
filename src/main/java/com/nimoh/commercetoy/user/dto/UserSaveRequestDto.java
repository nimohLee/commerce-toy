package com.nimoh.commercetoy.user.dto;

import com.nimoh.commercetoy.user.domain.User;
import lombok.Data;

@Data
public class UserSaveRequestDto {

    private String name;

    private String birth;

    private String email;

    private String loginId;

    private String phone;

    public User toEntity() {
        return User.builder()
                .name(name)
                .birth(birth)
                .email(email)
                .loginId(loginId)
                .phone(phone)
                .build();
    }

}
