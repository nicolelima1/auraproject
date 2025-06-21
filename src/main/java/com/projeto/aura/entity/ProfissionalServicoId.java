package com.projeto.aura.entity;

import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;
import jakarta.persistence.Column;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ProfissionalServicoId implements Serializable {

    @Column(name = "Id_Profissional")
    private Integer idProfissional;
    @Column(name = "Id_Servico")
    private Integer idServico;
}
