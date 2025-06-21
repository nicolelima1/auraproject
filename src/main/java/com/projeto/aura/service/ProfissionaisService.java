package com.projeto.aura.service;

import com.projeto.aura.entity.ProfissionaisEntity;
import com.projeto.aura.repository.ProfissionaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projeto.aura.entity.UsuarioEntity;
import com.projeto.aura.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ProfissionaisService {

    @Autowired
    private ProfissionaisRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;  

    public ProfissionaisEntity salvar(ProfissionaisEntity profissional) {
       
        Integer usuarioId = profissional.getUsuario().getId();

        UsuarioEntity usuarioGerenciado = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        profissional.setUsuario(usuarioGerenciado);

        return repository.save(profissional);
    }

    public Optional<ProfissionaisEntity> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public List<ProfissionaisEntity> listarTodos() {
        return repository.findAll();
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}
