package com.projeto.aura.controller;

import com.projeto.aura.entity.AgendamentoEntity;
import com.projeto.aura.service.AgendamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.service = agendamentoService;
    }

    @PostMapping
    public String agendar(@RequestParam int idCliente,
                          @RequestParam int idProfissional,
                          @RequestParam int idServico,
                          @RequestParam String data,  // formato yyyy-MM-dd
                          @RequestParam String hora,  // formato HH:mm:ss
                          @RequestParam(required = false) String status) {

        Date dataSql = Date.valueOf(data);
        Time horaSql = Time.valueOf(hora);

        return service.agendarServico(idCliente, idProfissional, idServico, dataSql, horaSql, status);
    }


    @GetMapping
    public List<AgendamentoEntity> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoEntity> buscarPorId(@PathVariable int id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoEntity> atualizar(@PathVariable int id, @RequestBody AgendamentoEntity agendamento) {
        return service.buscarPorId(id).map(existing -> {
            agendamento.setId(id);
            AgendamentoEntity atualizado = service.salvar(agendamento);
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
