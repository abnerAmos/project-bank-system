package com.banksystem.banksystem.services.implementacoes;

import com.banksystem.banksystem.domains.Cliente;
import com.banksystem.banksystem.domains.Conta;
import com.banksystem.banksystem.repositories.ContaRepositorio;
import com.banksystem.banksystem.repositories.impl.ContaRepositorioImpl;
import com.banksystem.banksystem.services.ServicoConta;

import java.util.Objects;
import java.util.Optional;

public class ImplConta implements ServicoConta {

    public ImplConta() {
        this.repositorio = new ContaRepositorioImpl();
    }

    private final ContaRepositorio repositorio;

    @Override
    public Optional<Conta> contaJaExistente(Cliente cliente) {
        Conta conta = repositorio.procurarContaPorDoc(cliente.getDocumento());
        return Objects.nonNull(conta) ? Optional.of(conta) : Optional.empty();
    }

    @Override
    public Conta criarConta(Conta conta) {
        return repositorio.criarConta(conta);
    }
}
