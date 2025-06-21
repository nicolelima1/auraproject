package com.projeto.aura.service;

import com.projeto.aura.entity.ServicosEntity;
import com.projeto.aura.repository.ServicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class ServicosService {

    @Autowired
    private ServicosRepository repository;

    public ServicosEntity salvar(ServicosEntity servico) {
        return repository.save(servico);
    }

    public Optional<ServicosEntity> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public List<ServicosEntity> listarTodos() {
        return repository.findAll();
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}
