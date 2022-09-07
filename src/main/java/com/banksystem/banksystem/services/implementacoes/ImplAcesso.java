package com.banksystem.banksystem.services.implementacoes;

import com.banksystem.banksystem.exceptions.ContaException;
import com.banksystem.banksystem.repositories.AcessoRepositorio;
import com.banksystem.banksystem.repositories.impl.AcessoRepositorioImpl;
import com.banksystem.banksystem.services.ServicoAcesso;

public class ImplAcesso implements ServicoAcesso {

    private final AcessoRepositorio repositorio;

    public ImplAcesso() {
        this.repositorio = new AcessoRepositorioImpl();
    }

    @Override
    public void criarAcesso(Integer senha, Integer idCliente, Integer idConta) {

        repositorio.criarSenha(senha, idCliente, idConta);

    }

    @Override
    public boolean senhaCorreta(Integer senha, Integer idCliente, Integer idConta) {
        Integer senhaDatabase = repositorio.getSenha(idCliente, idConta)
                .orElseThrow(() -> new ContaException("SENHA NÃO CADASTRADA"));
        return senha.equals(senhaDatabase);
    }
}
