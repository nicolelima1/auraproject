package com.projeto.aura.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projeto.aura.entity.ClienteEntity;

@Repository
public interface ClienteRepository
 extends JpaRepository<ClienteEntity, Integer>{

}

