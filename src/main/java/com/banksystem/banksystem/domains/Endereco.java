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
public class Endereco implements Serializable {

    private int id;
    private String cidade;
    private String estado;
    private String rua;
    private String bairro;
    private String numeroCasa;
    private int cep;
    private String complemento;
}
