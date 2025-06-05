package com.architecturemvc.Login.controller.dtos;

import java.util.List;

public record UsuarioDto(
        String email,
        String login,
        String senha,
        List<String>roles) {
}
