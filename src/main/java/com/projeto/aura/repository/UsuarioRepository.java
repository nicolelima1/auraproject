package com.projeto.aura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projeto.aura.entity.UsuarioEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    Optional<UsuarioEntity> findByEmail(String email);

    Optional<UsuarioEntity> findByNome(String nome);

    List<UsuarioEntity> findByNomeContainingIgnoreCase(String nome);
}
