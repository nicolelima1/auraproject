package com.projeto.aura.controller;

import com.projeto.aura.entity.UsuarioEntity;
import com.projeto.aura.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<UsuarioEntity> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> buscarPorId(@PathVariable int id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public UsuarioEntity criar(@RequestBody UsuarioEntity usuario) {
        return service.salvar(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioEntity> atualizar(@PathVariable int id, @RequestBody UsuarioEntity usuario) {
        return service.buscarPorId(id).map(existing -> {
            usuario.setId(id);
            UsuarioEntity atualizado = service.salvar(usuario);
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
