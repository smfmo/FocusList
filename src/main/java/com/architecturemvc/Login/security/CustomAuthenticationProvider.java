package com.architecturemvc.Login.security;

import com.architecturemvc.Login.model.Usuario;
import com.architecturemvc.Login.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        Usuario usuario = usuarioService.obterPorLogin(username);
        if (usuario == null) {
            throw new BadCredentialsException("Usuário no encontrado");
        }
        if (!passwordEncoder.matches(password, usuario.getSenha())) {
            throw new BadCredentialsException("Senha incorreta");
        }

        return new UsernamePasswordAuthenticationToken(
                usuario,
                password,
                usuario.getAuthorities()
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
