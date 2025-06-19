package com.architecturemvc.Login.service;

import com.architecturemvc.Login.model.Tarefas;
import com.architecturemvc.Login.model.Usuario;
import com.architecturemvc.Login.repository.TarefasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;

    public List<Tarefas> listarTarefasDoUsuario(UUID usuarioId){
        return tarefasRepository.findByUsuarioId(usuarioId);
    }

    public void criarTarefa(Tarefas tarefas, Usuario usuario){
        tarefas.setUsuario(usuario);
        tarefasRepository.save(tarefas);
    }

    public void deletar(UUID tarefaId){
        tarefasRepository.deleteById(tarefaId);
    }

    public void marcarComoConluido(UUID id){
        Tarefas tarefa = tarefasRepository.findById(id).orElseThrow();
        tarefa.setConcluida(!tarefa.isConcluida());
        tarefasRepository.save(tarefa);
    }
}
