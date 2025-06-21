package com.projeto.aura.controller;

import com.projeto.aura.entity.ClienteEntity;
import com.projeto.aura.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public List<ClienteEntity> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteEntity> buscarPorId(@PathVariable int id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ClienteEntity criar(@RequestBody ClienteEntity cliente) {
        return service.salvar(cliente);
    }

  @PutMapping("/{id}")
    public ResponseEntity<ClienteEntity> atualizar(@PathVariable int id, @RequestBody ClienteEntity cliente) {
        return service.buscarPorId(id).map(existing -> {
        cliente.setId(id);
        ClienteEntity atualizado = service.salvar(cliente);
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
