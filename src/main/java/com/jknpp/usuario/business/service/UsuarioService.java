package com.jknpp.usuario.business.service;

import com.jknpp.usuario.business.dto.UsuarioDto;
import com.jknpp.usuario.infrastructure.entity.Usuario;
import com.jknpp.usuario.infrastructure.mapper.UsuarioMapper;
import com.jknpp.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioDto salvarUsuario(UsuarioDto usuarioDto){
        Usuario usuario = usuarioMapper.toEntity(usuarioDto);
        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }

}
