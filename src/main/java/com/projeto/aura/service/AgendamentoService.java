package com.projeto.aura.service;

import com.projeto.aura.entity.AgendamentoEntity;
import com.projeto.aura.entity.ClienteEntity;
import com.projeto.aura.entity.ProfissionalServicoEntity;
import com.projeto.aura.entity.ProfissionalServicoId;
import com.projeto.aura.repository.AgendamentoRepository;
import com.projeto.aura.repository.ClienteRepository;
import com.projeto.aura.repository.ProfissionalServicoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

     @PersistenceContext
    private EntityManager entityManager;

    public String agendarServico(int idCliente, int idProfissional, int idServico, java.sql.Date data, java.sql.Time hora, String status) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_agendar_servico");


        query.registerStoredProcedureParameter("id_cliente", Integer.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("id_profissional", Integer.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("id_servico", Integer.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("data", java.sql.Date.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("hora", java.sql.Time.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("status", String.class, jakarta.persistence.ParameterMode.IN);

        query.setParameter("id_cliente", idCliente);
        query.setParameter("id_profissional", idProfissional);
        query.setParameter("id_servico", idServico);
        query.setParameter("data", data);
        query.setParameter("hora", hora);
        query.setParameter("status", status);

        query.execute();

        Object resultado = query.getSingleResult(); 

        return resultado != null ? resultado.toString() : "Nenhuma mensagem retornada";
    }


 @Autowired
private AgendamentoRepository repository;
@Autowired
private ClienteRepository clienteRepository;

@Autowired
private ProfissionalServicoRepository profissionalServicoRepository;

   public AgendamentoEntity salvar(AgendamentoEntity agendamento) {
    if (agendamento.getCliente() == null || agendamento.getCliente().getId() == 0) {
        throw new RuntimeException("ID do cliente é obrigatório");
    }

    if (agendamento.getProfissionalServico() == null || agendamento.getProfissionalServico().getId() == null) {
        throw new RuntimeException("ID do Profissional/Serviço é obrigatório");
    }

   
    ClienteEntity cliente = clienteRepository.findById(agendamento.getCliente().getId())
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    agendamento.setCliente(cliente);


    ProfissionalServicoId id = agendamento.getProfissionalServico().getId();
    ProfissionalServicoEntity profissionalServico = profissionalServicoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("ProfissionalServico não encontrado"));
    agendamento.setProfissionalServico(profissionalServico);

    return repository.save(agendamento);
}
    public Optional<AgendamentoEntity> buscarPorId(int id) {
        return repository.findById(id);
    }

    public List<AgendamentoEntity> listarTodos() {
        return repository.findAll();
    }

    public void deletar(int id) {
        repository.deleteById(id);
    }
}
