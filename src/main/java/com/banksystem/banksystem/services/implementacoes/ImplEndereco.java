package com.banksystem.banksystem.services.implementacoes;

import com.banksystem.banksystem.domains.Endereco;
import com.banksystem.banksystem.repositories.EnderecoRepositorio;
import com.banksystem.banksystem.services.ServicoEndereco;

import java.util.Optional;

public class ImplEndereco implements ServicoEndereco {

    private final EnderecoRepositorio enderecoRepositorio;

    public ImplEndereco(EnderecoRepositorio enderecoRepositorio) {
        this.enderecoRepositorio = enderecoRepositorio;
    }

    @Override
    public Endereco criarEndereco(Endereco endereco) {
        Endereco enderecoCriado = enderecoRepositorio.criarEndereco(endereco);
        return endereco;
    }

    @Override
    public Optional<Endereco> construtorEndereco(String endereco) {

        String[] token = endereco.split(",");

        String rua;
        String numero;
        String bairro;
        String cidade;
        String estado;
        String cep;
        String complemento = "";

        try {
            rua = token[0].trim();
            numero = token[1].trim();
            bairro = token[2].trim();
            cidade = token[3].trim();
            estado = token[4].trim();
            cep = token[5].trim();
            try {
                complemento = token[6];
            } catch (ArrayIndexOutOfBoundsException e) {
                complemento = null;
            }

            rua.matches("(?=^.{2,60}$)^[A-Z][a-z]+(?:[ ](?:das?|dos?|de|e|[A-Z][a-z]+))*$");
            numero.matches("\\d{1,5}[A-Z]?");
            bairro.matches("(?=^.{2,30}$)^[A-Z][a-z]+(?:[ ](?:das?|dos?|de|e|[A-Z][a-z]+))*$");
            cidade.matches("([A-Z][a-z]{2,10}([ ]?[do|de]?|[A-Z][a-z]{3,8})*)");
            estado.matches("[A-Z]{2}");
            cep.matches("\\d{5}-?\\d{3}");
            complemento.matches("((\\s?[A-Z][a-z]{2,12}([ ]\\d{1,4}[A-Z]?|[A-Z])?)*)?");

        } catch (ArrayIndexOutOfBoundsException e) {
            return Optional.empty();
        }

        Endereco EndObj = new Endereco();
        EndObj.setRua(rua);
        EndObj.setNumeroCasa(numero);
        EndObj.setBairro(bairro);
        EndObj.setCidade(cidade);
        EndObj.setEstado(estado);
        EndObj.setCep(Integer.parseInt(cep));
        EndObj.setComplemento(complemento);

        return Optional.of(EndObj);
    }
}
