package com.banksystem.banksystem.domains;

import com.banksystem.banksystem.enums.TipoConta;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Conta implements Serializable {

    private Integer id;
    private Integer numeroConta;
    private Integer clienteId;
    private Integer agencia;
    private Double saldo;
    private LocalDateTime dataRegistro;
    private TipoConta tipoConta;
    private LocalDateTime dataDesativacao;

    public Conta(Integer clienteId, TipoConta tipoConta) {

        this.numeroConta = new java.util.Random().nextInt((1999 - 1001) + 1) + 1001;
        this.clienteId = clienteId;
        this.agencia = 1;
        this.saldo = 0.0;
        this.dataRegistro = LocalDateTime.now();
        this.tipoConta = tipoConta;
    }
}
