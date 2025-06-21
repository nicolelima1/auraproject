package com.projeto.aura.service;

import com.projeto.aura.entity.ProfissionalServicoEntity;
import com.projeto.aura.entity.ProfissionaisEntity;
import com.projeto.aura.entity.ServicosEntity;
import com.projeto.aura.entity.ViewProfissionaisServicos;
import com.projeto.aura.entity.ProfissionalServicoId;
import com.projeto.aura.repository.ProfissionalServicoRepository;
import com.projeto.aura.repository.ViewProfissionaisServicosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfissionalServicoService {

    private final ProfissionalServicoRepository repository;

    public ProfissionalServicoService(ProfissionalServicoRepository repository) {
        this.repository = repository;
    }

    public ProfissionalServicoEntity salvar(ProfissionalServicoEntity profissionalServico) {
        return repository.save(profissionalServico);
    }

    public Optional<ProfissionalServicoEntity> buscarPorId(ProfissionalServicoId id) {
        return repository.findById(id);
    }

    public List<ProfissionalServicoEntity> listarTodos() {
        return repository.findAll();
    }

    public void deletar(ProfissionalServicoId id) {
        repository.deleteById(id);
    }

    public List<ProfissionalServicoEntity> buscarPorProfissional(ProfissionaisEntity profissional) {
        return repository.findByProfissional(profissional);
    }

    public List<ProfissionalServicoEntity> buscarPorServico(ServicosEntity servico) {
        return repository.findByServico(servico);
    }

    @Autowired
    private ViewProfissionaisServicosRepository viewRepo;

    public List<ViewProfissionaisServicos> listarTudo() {
    return viewRepo.findAll();
}
}
