package com.jknpp.usuario.infrastructure.repository;

import com.jknpp.usuario.infrastructure.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
    Optional<Telefone> findById(Long id);
}
