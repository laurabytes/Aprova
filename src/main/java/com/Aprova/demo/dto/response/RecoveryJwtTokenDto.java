package com.Aprova.demo.dto.response;

public record RecoveryJwtTokenDto(
        String token,
        Integer id,
        String email,
        String nome
) {
}