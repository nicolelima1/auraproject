package com.projeto.aura.DTO;

import lombok.Data;

@Data
public class AgendamentoDTO {
    private int idCliente;
    private int idProfissional;
    private int idServico;
    private String data;  
    private String hora;  
    private String status;
}