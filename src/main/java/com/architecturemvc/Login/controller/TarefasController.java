package com.architecturemvc.Login.controller;

import com.architecturemvc.Login.controller.dtos.TarefasDto;
import com.architecturemvc.Login.controller.mappers.TarefasMapper;
import com.architecturemvc.Login.model.Usuario;
import com.architecturemvc.Login.service.TarefasService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tarefas")
public class TarefasController {

    private final TarefasService tarefasService;
    private final TarefasMapper tarefasMapper;

    @GetMapping
    public String listarTarefas(Model model,
                                @AuthenticationPrincipal Usuario usuario){
        model.addAttribute("tarefas",
                tarefasService.listarTarefasDoUsuario(usuario.getId()));
        return "tarefas";
    }

    @PostMapping
    public String salvarTarefas(@ModelAttribute TarefasDto dto,
                                @AuthenticationPrincipal Usuario usuario){
        var tarefas = tarefasMapper.toEntity(dto);
        tarefasService.criarTarefa(tarefas, usuario);

        return "redirect:/tarefas";
    }

    @PostMapping("/status")
    public String marcarComoConcluido(@RequestParam UUID id){
        tarefasService.marcarComoConluido(id);
        return "redirect:/tarefas";
    }

    @PostMapping("/excluir")
    public String excluir(@RequestParam UUID id){
        tarefasService.deletar(id);
        return "redirect:/tarefas";
    }
}
