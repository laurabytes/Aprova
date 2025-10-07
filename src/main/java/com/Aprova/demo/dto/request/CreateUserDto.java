package com.Aprova.demo.dto.request;

import com.Aprova.demo.Entity.RoleName;

public record CreateUserDto(

        String nome,
        String email,
        String password,
        RoleName role
) {
}