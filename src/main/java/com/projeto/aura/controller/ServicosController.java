package com.projeto.aura.controller;

import com.projeto.aura.entity.ServicosEntity;
import com.projeto.aura.repository.ServicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servicos")
public class ServicosController {

    @Autowired
    private ServicosRepository servicosRepository;

    @GetMapping
    public List<ServicosEntity> getAll() {
        return servicosRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicosEntity> getById(@PathVariable int id) {
        Optional<ServicosEntity> servico = servicosRepository.findById(id);
        return servico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ServicosEntity create(@RequestBody ServicosEntity servico) {
        return servicosRepository.save(servico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicosEntity> update(@PathVariable int id, @RequestBody ServicosEntity servicoAtualizado) {
        return servicosRepository.findById(id)
                .map(servico -> {
                    servico.setDescricao(servicoAtualizado.getDescricao());
                    ServicosEntity atualizado = servicosRepository.save(servico);
                    return ResponseEntity.ok(atualizado);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (servicosRepository.existsById(id)) {
            servicosRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
