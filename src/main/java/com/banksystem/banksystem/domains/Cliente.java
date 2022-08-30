package com.banksystem.banksystem.domains;

import com.banksystem.banksystem.enums.TipoDocumento;
import com.banksystem.banksystem.enums.TipoPessoa;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cliente implements Serializable {

    private Integer id;
    private String nome;
    private LocalDate nascimento;
    private String telefone;
    private String email;
    private TipoPessoa tipoPessoa;
    private TipoDocumento tipoDocumento;
    private String documento;
    private Integer enderecoId;
}
