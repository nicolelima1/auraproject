package com.projeto.aura.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "agendamentos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AgendamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Agenda")
    private Integer id;

    @Column(name = "data")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate data;

    @Column(name = "hora")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime hora;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "Id_Cliente")
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "Id_Profissional", referencedColumnName = "Id_Profissional"),
        @JoinColumn(name = "Id_Servico", referencedColumnName = "Id_Servico")
    })
    private ProfissionalServicoEntity profissionalServico;
}
