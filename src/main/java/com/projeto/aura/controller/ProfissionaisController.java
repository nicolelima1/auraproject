package com.projeto.aura.controller;

import com.projeto.aura.entity.ProfissionaisEntity;
import com.projeto.aura.service.ProfissionaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profissionais")
public class ProfissionaisController {

    @Autowired
    private ProfissionaisService service;

    @GetMapping
    public List<ProfissionaisEntity> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfissionaisEntity> buscarPorId(@PathVariable int id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProfissionaisEntity criar(@RequestBody ProfissionaisEntity profissional) {
        return service.salvar(profissional);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfissionaisEntity> atualizar(@PathVariable int id, @RequestBody ProfissionaisEntity profissional) {
        return service.buscarPorId(id).map(existing -> {
            profissional.setId(id);
            ProfissionaisEntity atualizado = service.salvar(profissional);
            return ResponseEntity.ok(atualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        if (service.buscarPorId(id).isPresent()) {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
