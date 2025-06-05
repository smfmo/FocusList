package com.architecturemvc.Login.controller;

import com.architecturemvc.Login.controller.dtos.UsuarioDto;
import com.architecturemvc.Login.controller.mappers.UsuarioMapper;
import com.architecturemvc.Login.model.Usuario;
import com.architecturemvc.Login.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
        if (error != null){
            model.addAttribute("error", "Crendenciais inválidas");
        }
        if (logout != null){
            model.addAttribute("logout", "Você foi desconectado com sucesso");
        }

        return "login";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastro(@ModelAttribute UsuarioDto dto) {
        var usuario = usuarioMapper.toEntity(dto);
        usuarioService.salvar(usuario);
        return "redirect:/login?cadastroSuccess=true";
    }
}
