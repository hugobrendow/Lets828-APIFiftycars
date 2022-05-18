package com.letscode.fiftycars.dto.cliente;

import java.io.Serializable;

public class ClientePUT implements Serializable {

    private Integer codigo;
    private String nome;
    private String dataNascimento;
    private char sexo;

    public Integer getCodigo() { return codigo; }

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
