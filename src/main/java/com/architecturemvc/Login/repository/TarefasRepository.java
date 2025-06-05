package com.architecturemvc.Login.repository;

import com.architecturemvc.Login.model.Tarefas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface TarefasRepository extends JpaRepository<Tarefas, UUID> {
    List<Tarefas> findByUsuarioId(UUID usuarioId);
}
