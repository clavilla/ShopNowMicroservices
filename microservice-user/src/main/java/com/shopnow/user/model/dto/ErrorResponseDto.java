package com.shopnow.user.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ErrorResponseDto {

    private String mensaje;

    public ErrorResponseDto(String mensaje) {
        this.mensaje = mensaje;
    }

}
