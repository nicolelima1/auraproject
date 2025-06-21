package com.projeto.aura.service;


import com.projeto.aura.entity.ClienteEntity;
import com.projeto.aura.entity.UsuarioEntity;
import com.projeto.aura.repository.ClienteRepository;
import com.projeto.aura.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Salvar um novo cliente
    public ClienteEntity salvar(ClienteEntity cliente) {
        UsuarioEntity usuario = usuarioRepository.findById(cliente.getUsuario().getId())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        cliente.setUsuario(usuario);
        return repository.save(cliente);
    }

    public Optional<ClienteEntity> buscarPorId(int id) {
        return repository.findById(id);
    }

    public List<ClienteEntity> listarTodos() {
        return repository.findAll();
    }

    public void deletar(int id) {
        repository.deleteById(id);
    }
}
