package com.projeto.aura.controller;

import com.projeto.aura.DTO.AgendamentoDTO;
import com.projeto.aura.entity.AgendamentoEntity;
import com.projeto.aura.entity.ClienteEntity;
import com.projeto.aura.entity.ProfissionalServicoEntity;
import com.projeto.aura.entity.ProfissionalServicoId;
import com.projeto.aura.service.AgendamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.service = agendamentoService;
    }

    @PostMapping
    public String agendar(@RequestBody AgendamentoDTO dto) {
    Date dataSql = Date.valueOf(dto.getData());
    Time horaSql = Time.valueOf(dto.getHora());

    return service.agendarServico(
            dto.getIdCliente(),
            dto.getIdProfissional(),
            dto.getIdServico(),
            dataSql,
            horaSql,
            dto.getStatus()
    );
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
public ResponseEntity<?> atualizar(@PathVariable int id, @RequestBody AgendamentoDTO dto) {
    Optional<AgendamentoEntity> existenteOpt = service.buscarPorId(id);

    if (existenteOpt.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    AgendamentoEntity agendamento = existenteOpt.get();

   
    ClienteEntity cliente = new ClienteEntity();
    cliente.setId(dto.getIdCliente());
    agendamento.setCliente(cliente);

    
    ProfissionalServicoId profServId = new ProfissionalServicoId();
    profServId.setIdProfissional(dto.getIdProfissional());
    profServId.setIdServico(dto.getIdServico());

    ProfissionalServicoEntity profServ = new ProfissionalServicoEntity();
    profServ.setId(profServId);
    agendamento.setProfissionalServico(profServ);

    agendamento.setData(LocalDate.parse(dto.getData()));
    agendamento.setHora(LocalTime.parse(dto.getHora()));
    agendamento.setStatus(dto.getStatus());

    
    AgendamentoEntity atualizado = service.salvar(agendamento);
    return ResponseEntity.ok(atualizado);
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
