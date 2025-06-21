package com.projeto.aura.entity;

import java.math.BigDecimal;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import jakarta.persistence.Entity;
import org.hibernate.annotations.Immutable;


@Entity
@Getter
@Immutable
@Table(name = "View_profissionais_servicos")
public class ViewProfissionaisServicos {
    @Id
    private Integer idProfissional;

    private String nome;
    private String telefone;
    private String email;
    private String cpf;
    private String servicosPrecos;
    private Integer qtdServicos;
    private BigDecimal precoMinimo;
    private BigDecimal precoMaximo;

}

