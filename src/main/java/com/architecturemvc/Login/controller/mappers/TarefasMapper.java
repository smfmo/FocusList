package com.architecturemvc.Login.controller.mappers;

import com.architecturemvc.Login.controller.dtos.TarefasDto;
import com.architecturemvc.Login.model.Tarefas;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasMapper {

    Tarefas toEntity(TarefasDto tarefasDto);

    TarefasDto toDto(Tarefas tarefas);
}
