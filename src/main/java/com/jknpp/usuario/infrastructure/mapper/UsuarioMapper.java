package com.jknpp.usuario.infrastructure.mapper;

import com.jknpp.usuario.business.dto.EnderecoDto;
import com.jknpp.usuario.business.dto.TelefoneDto;
import com.jknpp.usuario.business.dto.UsuarioDto;
import com.jknpp.usuario.infrastructure.entity.Endereco;
import com.jknpp.usuario.infrastructure.entity.Telefone;
import com.jknpp.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper {

    public UsuarioDto toDto(Usuario usuario){

        if (usuario == null) return null;

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setSenha(usuario.getSenha());
        if(usuario.getEnderecos() != null){
            List<EnderecoDto> enderecos = usuario.getEnderecos().stream()
                    .map(this::toDto)
                    .collect(Collectors.toList());
            usuarioDto.setEnderecos(enderecos);
        }

        if(usuario.getTelefones() != null){
            List<TelefoneDto> telefones = usuario.getTelefones().stream()
                    .map(this::toDto)
                    .collect(Collectors.toList());
            usuarioDto.setTelefones(telefones);
        }

        return usuarioDto;
    }

    public Usuario toEntity(UsuarioDto dto){

        if(dto == null) return null;

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        if(dto.getEnderecos() != null){
            List<Endereco> enderecos = dto.getEnderecos().stream()
                    .map(this::toEntity)
                    .collect(Collectors.toList());
            usuario.setEnderecos(enderecos);
        }

        if (dto.getTelefones() != null){
            List<Telefone> telefones = dto.getTelefones().stream()
                    .map(this::toEntity)
                    .collect(Collectors.toList());
            usuario.setTelefones(telefones);
        }

        return usuario;
    }

    public EnderecoDto toDto(Endereco endereco){
        EnderecoDto enderecoDto = new EnderecoDto();
        enderecoDto.setRua(endereco.getRua());
        enderecoDto.setNumero(endereco.getNumero());
        enderecoDto.setComplemento(endereco.getComplemento());
        enderecoDto.setCidade(endereco.getCidade());
        enderecoDto.setEstado(endereco.getEstado());
        enderecoDto.setCep(endereco.getCep());

        return enderecoDto;
    }

    public Endereco toEntity(EnderecoDto dto){
        Endereco endereco = new Endereco();
        endereco.setRua(dto.getRua());
        endereco.setNumero(dto.getNumero());
        endereco.setComplemento(dto.getComplemento());
        endereco.setCidade(dto.getCidade());
        endereco.setEstado(dto.getEstado());
        endereco.setCep(dto.getCep());

        return endereco;
    }

    public TelefoneDto toDto(Telefone telefone){
        TelefoneDto telefoneDto = new TelefoneDto();
        telefoneDto.setNumero(telefone.getNumero());
        telefoneDto.setDdd(telefone.getDdd());

        return telefoneDto;
    }

    public Telefone toEntity(TelefoneDto dto){
        Telefone telefone = new Telefone();
        telefone.setNumero(dto.getNumero());
        telefone.setDdd(dto.getDdd());

        return telefone;
    }
}
