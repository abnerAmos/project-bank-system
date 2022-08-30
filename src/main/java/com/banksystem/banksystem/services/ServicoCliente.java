package com.banksystem.banksystem.services;

import com.banksystem.banksystem.domains.Cliente;
import java.util.Optional;

public interface ServicoCliente {

    Optional<Cliente> construtorCliente (String cliente);

    Cliente criarCliente(Cliente cliente);
}
