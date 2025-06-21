package com.projeto.aura.controller;

import com.projeto.aura.entity.ProfissionalServicoEntity;
import com.projeto.aura.entity.ProfissionalServicoId;
import com.projeto.aura.entity.ViewProfissionaisServicos;
import com.projeto.aura.service.ProfissionalServicoService;
import com.projeto.aura.repository.ProfissionaisRepository;
import com.projeto.aura.repository.ServicosRepository;
import com.projeto.aura.repository.ViewProfissionaisServicosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profissionalservicos")
public class ProfissionalServicoController {

    @Autowired
    private ProfissionalServicoService service;

    @Autowired
    private ProfissionaisRepository profissionaisRepository;

    @Autowired
    private ServicosRepository servicosRepository;

    @GetMapping
    public List<ProfissionalServicoEntity> listarTodos() {
        return service.listarTodos();
    }
    @RestController
    @RequestMapping("/viewprofissionaisservicos")
    public class ViewProfissionaisServicosController {

    @Autowired
    private ViewProfissionaisServicosRepository repository;

    @GetMapping
    public List<ViewProfissionaisServicos> listarTudo() {
        return repository.findAll();
    }
}

    @GetMapping("/profissional/{idProfissional}")
    public ResponseEntity<List<ProfissionalServicoEntity>> buscarPorProfissional(@PathVariable int idProfissional) {
        return profissionaisRepository.findById(idProfissional)
                .map(service::buscarPorProfissional)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/servico/{idServico}")
    public ResponseEntity<List<ProfissionalServicoEntity>> buscarPorServico(@PathVariable int idServico) {
        return servicosRepository.findById(idServico)
                .map(service::buscarPorServico)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{idProfissional}/{idServico}")
    public ResponseEntity<ProfissionalServicoEntity> buscarPorId(
            @PathVariable int idProfissional,
            @PathVariable int idServico) {

        ProfissionalServicoId id = new ProfissionalServicoId(idProfissional, idServico);

        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProfissionalServicoEntity criar(@RequestBody ProfissionalServicoEntity profissionalServico) {
        return service.salvar(profissionalServico);
    }

    @PutMapping("/{idProfissional}/{idServico}")
    public ResponseEntity<ProfissionalServicoEntity> atualizar(
            @PathVariable int idProfissional,
            @PathVariable int idServico,
            @RequestBody ProfissionalServicoEntity profissionalServico) {

        ProfissionalServicoId id = new ProfissionalServicoId(idProfissional, idServico);

        return service.buscarPorId(id).map(existing -> {
            profissionalServico.setId(id);
            ProfissionalServicoEntity atualizado = service.salvar(profissionalServico);
            return ResponseEntity.ok(atualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{Id_Profissional}/{Id_Servico}")
    public ResponseEntity<Void> deletar(
            @PathVariable int Id_Profissional,
            @PathVariable int Id_Servico) {

        ProfissionalServicoId id = new ProfissionalServicoId(Id_Profissional, Id_Servico);

        if (service.buscarPorId(id).isPresent()) {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
