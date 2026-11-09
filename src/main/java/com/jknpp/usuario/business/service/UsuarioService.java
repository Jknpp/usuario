package com.jknpp.usuario.business.service;

import com.jknpp.usuario.business.dto.UsuarioDto;
import com.jknpp.usuario.infrastructure.entity.Usuario;
import com.jknpp.usuario.infrastructure.exceptions.ConflictException;
import com.jknpp.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.jknpp.usuario.infrastructure.mapper.UsuarioMapper;
import com.jknpp.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDto salvarUsuario(UsuarioDto usuarioDto){
        if (usuarioRepository.existsByEmail(usuarioDto.getEmail())) {
            throw new ConflictException("Email já cadastrado: " + usuarioDto.getEmail());
        }

            Usuario usuario = usuarioMapper.toEntity(usuarioDto);
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    public UsuarioDto buscarUsuario(String email){
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Email não encontrado " + email));
        return usuarioMapper.toDto(usuario);
    }


    public void deletarUsuario(String email){
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Email não encontrado " + email));

        usuarioRepository.deleteByEmail(usuario.getEmail());
    }

}

