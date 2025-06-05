package com.architecturemvc.Login.controller.mappers;

import com.architecturemvc.Login.controller.dtos.UsuarioDto;
import com.architecturemvc.Login.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDto usuarioDto);

    UsuarioDto toDto(Usuario usuario);
}
