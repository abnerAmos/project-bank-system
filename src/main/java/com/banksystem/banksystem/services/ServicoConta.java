package com.banksystem.banksystem.services;

import com.banksystem.banksystem.domains.Cliente;
import com.banksystem.banksystem.domains.Conta;

import java.util.Optional;

public interface ServicoConta {

    Optional<Conta> contaJaExistente(Cliente cliente);

    Conta criarConta(Conta conta);
}
