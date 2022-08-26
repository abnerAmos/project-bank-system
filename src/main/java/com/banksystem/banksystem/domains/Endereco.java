package com.banksystem.banksystem.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Endereco implements Serializable {     // forma em que o end. deve ser construido

    private Integer id;
    private String cidade;
    private String estado;
    private String rua;
    private String bairro;
    private String numeroCasa;
    private Integer cep;
    private String complemento;
}
