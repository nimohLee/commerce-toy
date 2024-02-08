package com.nimoh.commercetoy.user.dto;

import lombok.Data;

@Data
public class UserUpdateRequestDto {

    private String name;
    private String phone;
    private String loginId;
    private String password;
    private String email;
}
