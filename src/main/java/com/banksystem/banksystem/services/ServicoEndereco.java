package com.banksystem.banksystem.services;

import com.banksystem.banksystem.domains.Endereco;
import java.util.Optional;

public interface ServicoEndereco {

    void criarEndereco(String endereco);

    Optional<Endereco> construtorEndereco(String endereco);

}
