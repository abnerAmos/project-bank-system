package com.banksystem.banksystem.services.implementacoes;

import com.banksystem.banksystem.domains.Cliente;
import com.banksystem.banksystem.domains.Conta;
import com.banksystem.banksystem.repositories.ContaRepositorio;
import com.banksystem.banksystem.services.ServicoConta;

public class ImplConta implements ServicoConta {

    public ImplConta(ContaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    private ContaRepositorio repositorio;

    @Override
    public boolean contaJaExistente(Cliente cliente) {
        return false;
    }

    @Override
    public Conta criarConta(Conta conta) {
        return null;
    }
}
