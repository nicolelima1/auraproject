package com.projeto.aura.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projeto.aura.entity.AgendamentoEntity;

@Repository
public interface AgendamentoRepository
 extends JpaRepository<AgendamentoEntity, Integer>{

}

