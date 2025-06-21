package com.projeto.aura.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "Profissional_Servico")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfissionalServicoEntity {

    @EmbeddedId
    private ProfissionalServicoId id;

    @ManyToOne
    @MapsId("idProfissional")  
    @JoinColumn(name = "Id_Profissional")
    private ProfissionaisEntity profissional;

    @ManyToOne
    @MapsId("idServico")  
    @JoinColumn(name = "Id_Servico")
    private ServicosEntity servico;

    @Column(name = "preco", nullable = false)
    private BigDecimal preco;
}
