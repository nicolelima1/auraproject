package com.projeto.aura.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projeto.aura.entity.ProfissionaisEntity;

@Repository
public interface ProfissionaisRepository
 extends JpaRepository<ProfissionaisEntity, Integer>{

}

