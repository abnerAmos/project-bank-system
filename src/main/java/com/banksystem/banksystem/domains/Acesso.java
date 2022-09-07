package com.banksystem.banksystem.domains;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Acesso implements Serializable {

    private Integer senha;

}
