package com.banksystem.banksystem.services;

import com.banksystem.banksystem.domains.Cliente;
import com.banksystem.banksystem.domains.Conta;

public interface ServicoConta {

    boolean contaJaExistente(Cliente cliente);

    Conta criarConta(Conta conta);
}
