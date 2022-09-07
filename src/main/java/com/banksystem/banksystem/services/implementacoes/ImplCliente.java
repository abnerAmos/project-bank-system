package com.banksystem.banksystem.services.implementacoes;

import com.banksystem.banksystem.domains.Cliente;
import com.banksystem.banksystem.enums.TipoDocumento;
import com.banksystem.banksystem.enums.TipoPessoa;
import com.banksystem.banksystem.repositories.ClienteRepositorio;
import com.banksystem.banksystem.repositories.impl.ClienteRepositorioImpl;
import com.banksystem.banksystem.services.ServicoCliente;
import com.banksystem.banksystem.utils.ValidandoData;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.Optional;

public class ImplCliente implements ServicoCliente {

    private final ClienteRepositorio clienteRepositorio;

    public ImplCliente() {
        this.clienteRepositorio = new ClienteRepositorioImpl();
    }

    @Override
    public Cliente criarCliente(Cliente cliente) {
        Cliente clienteCriado = clienteRepositorio.criarCliente(cliente);
        return cliente;
    }

    @Override
    public Optional<Cliente> construtorCliente(String cliente) {

        String[] token = cliente.split(",");

        String nome;
        String nascimento;
        String telefone;
        String email;
        String tipoPessoa;
        String tipoDocumento;
        String documento;
        LocalDate dataNascimento;

        try {
            nome = token[0].trim();
            nascimento = token[1].trim();
            telefone = token[2].trim();
            email = token[3].trim();
            tipoPessoa = token[4].trim();
            tipoDocumento = token[5].trim();
            documento = token[6].trim();

            nome.matches("([A-Z][a-z]{2,20}([ ]?(da|de|do|das|dos)?|[A-Z][a-z]{2,20})*)");
            telefone.matches("\\d{11}");
            email.matches("^[a-z0-9._-]+@[a-z0-9.]+[com][br]?");
            tipoPessoa.matches("PF|PJ");
            tipoDocumento.matches("CPF|CNPJ");
            documento.matches("\\d{11}|\\d{14}");

            if (StringUtils.isBlank(nascimento)) {
                return Optional.empty();
            } else {
                try {
                    dataNascimento = ValidandoData.stringToLocalDate(nascimento, "dd/MM/yyyy");
                } catch (Exception e) {
                    return Optional.empty();
                }
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            return Optional.empty();
        }

        Cliente clienteBuild = Cliente
                .builder()
                .nome(nome)
                .nascimento(dataNascimento)
                .telefone(telefone)
                .email(email)
                .tipoPessoa(TipoPessoa.valueOf(tipoPessoa))
                .tipoDocumento(TipoDocumento.valueOf(tipoDocumento))
                .documento(documento)
                .build();

        return Optional.of(clienteBuild);
    }
}