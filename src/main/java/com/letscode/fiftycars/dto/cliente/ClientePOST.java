package com.letscode.fiftycars.dto.cliente;

import java.io.Serializable;

public class ClientePOST implements Serializable {

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
