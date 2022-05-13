package com.letscode.fiftycars.dto.cliente;

import java.io.Serializable;

public class ClienteResponseDTO implements Serializable {

    private String cliente;
    private String dataAniversario;
    private char genero;

    public ClienteResponseDTO(String cliente, String dataAniversario, char genero) {
        this.cliente = cliente;
        this.dataAniversario = dataAniversario;
        this.genero = genero;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDataAniversario() {
        return dataAniversario;
    }

    public void setDataAniversario(String dataAniversario) {
        this.dataAniversario = dataAniversario;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }
}
