package com.letscode.fiftycars.dto.cliente;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class ClientePOST implements Serializable {

    @NotNull(message = "O nome do cliente deve ser informado!")
    private String nome;

    private String dataNascimento;

    private char sexo;

}
