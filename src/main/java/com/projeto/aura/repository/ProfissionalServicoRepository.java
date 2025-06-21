package com.projeto.aura.repository;

import com.projeto.aura.entity.ProfissionaisEntity;
import com.projeto.aura.entity.ProfissionalServicoEntity;
import com.projeto.aura.entity.ProfissionalServicoId;
import com.projeto.aura.entity.ServicosEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfissionalServicoRepository extends JpaRepository<ProfissionalServicoEntity, ProfissionalServicoId> {

   List<ProfissionalServicoEntity> findByProfissional(ProfissionaisEntity profissional);

    List<ProfissionalServicoEntity> findByServico(ServicosEntity servico);
}
