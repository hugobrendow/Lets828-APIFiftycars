package com.letscode.fiftycars.dto.cliente;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


public class ClientePOST implements Serializable {

    @NotNull(message = "O nome do cliente deve ser informado!")
    private String nome;

    private String dataNascimento;

    private char sexo;

    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public char getSexo() {
        return sexo;
    }

}
