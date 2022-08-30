package com.banksystem.banksystem.services;

import com.banksystem.banksystem.domains.Cliente;
import java.util.Optional;

public interface ServicoCliente {

    Cliente criarCliente(Cliente cliente);

    Optional<Cliente> construtorCliente (String cliente);
}
