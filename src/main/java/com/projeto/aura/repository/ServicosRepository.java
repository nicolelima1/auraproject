package com.projeto.aura.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projeto.aura.entity.ServicosEntity;

@Repository
public interface ServicosRepository
 extends JpaRepository<ServicosEntity, Integer>{

}

