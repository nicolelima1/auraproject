package com.projeto.aura.service;

import com.projeto.aura.entity.UsuarioEntity;
import com.projeto.aura.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioEntity salvar(UsuarioEntity usuario) {
        return repository.save(usuario);
    }

    public Optional<UsuarioEntity> buscarPorId(int id) {
        return repository.findById(id);
    }

    public List<UsuarioEntity> listarTodos() {
        return repository.findAll();
    }

    public void deletar(int id) {
        repository.deleteById(id);
    }

    public Optional<UsuarioEntity> buscarPorEmail(String email) {
        return repository.findByEmail(email);
    }

    public Optional<UsuarioEntity> buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    public List<UsuarioEntity> buscarPorNomeContendo(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }
}
