package com.jknpp.usuario.controller;

import com.jknpp.usuario.business.dto.UsuarioDto;
import com.jknpp.usuario.business.service.UsuarioService;
import com.jknpp.usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<UsuarioDto> salvaUsuario(@RequestBody UsuarioDto usuarioDto){
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDto));
    }


    @PostMapping("/login")
    public String login(@RequestBody UsuarioDto dto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha())
        );
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping
    public ResponseEntity<UsuarioDto> buscarUsuario(String email){
        return ResponseEntity.ok(usuarioService.buscarUsuario(email));
    }

    @PutMapping
    public ResponseEntity<UsuarioDto> atualizaDadosUsuario(@RequestHeader("Authorization") String token,
                                                           @RequestBody UsuarioDto dto){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, dto));
    }

    @DeleteMapping
    public void deletarUsuario(String email){
        usuarioService.deletarUsuario(email);
    }

}
