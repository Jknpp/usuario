package com.jknpp.usuario.business.service;

import com.jknpp.usuario.business.dto.UsuarioDto;
import com.jknpp.usuario.infrastructure.entity.Endereco;
import com.jknpp.usuario.infrastructure.entity.Telefone;
import com.jknpp.usuario.infrastructure.entity.Usuario;
import com.jknpp.usuario.infrastructure.exceptions.ConflictException;
import com.jknpp.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.jknpp.usuario.infrastructure.mapper.UsuarioMapper;
import com.jknpp.usuario.infrastructure.repository.UsuarioRepository;
import com.jknpp.usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

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

    public UsuarioDto atualizaDadosUsuario(String token, UsuarioDto dto) {
        String email = jwtUtil.extractUsername(token.substring(7));

        dto.setSenha(dto.getSenha() != null ? passwordEncoder.encode(dto.getSenha()) : null);

        Usuario usuarioEntity = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Email não encontrado " + email));

        Usuario usuario = usuarioMapper.updateUsuario(dto, usuarioEntity);

        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }


    public void deletarUsuario(String email){
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Email não encontrado " + email));

        usuarioRepository.deleteByEmail(usuario.getEmail());
    }

}

