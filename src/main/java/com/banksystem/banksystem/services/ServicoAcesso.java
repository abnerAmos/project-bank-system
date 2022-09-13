package com.banksystem.banksystem.services;

public interface ServicoAcesso {

    boolean senhaCorreta(Integer senha, Integer idCliente, Integer idConta);

    void criarAcesso(Integer senha, Integer idCliente, Integer idConta);
}