package com.banksystem.banksystem.services;

import com.banksystem.banksystem.domains.Endereco;
import java.util.Optional;

public interface ServicoEndereco {

    Endereco criarEndereco(Endereco endereco);

    Optional<Endereco> construtorEndereco(String endereco);

}
