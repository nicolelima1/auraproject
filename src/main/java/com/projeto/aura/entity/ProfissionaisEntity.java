package com.projeto.aura.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="profissionais")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ProfissionaisEntity {

    @Id
    @Column(name = "Id_Profissional")
    private Integer id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "Id_Profissional")
    private UsuarioEntity usuario;

    @Column(name = "cpf")
    private String cpf;
}
