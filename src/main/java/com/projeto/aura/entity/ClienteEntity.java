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
@Table(name="clientes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ClienteEntity {

    @Id
    @Column(name = "Id_Cliente")
    private int id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "Id_Cliente")
    private UsuarioEntity usuario;

    @Column (name = "genero")
    private String genero;
    @Column (name = "data_nascimento")
    private int dataNascimento;


}
