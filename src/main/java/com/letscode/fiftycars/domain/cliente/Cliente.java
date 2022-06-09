package com.letscode.fiftycars.domain.cliente;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/*@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder*/
@Table(name = "TB_CLIENTES")
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_CLI")
    private Integer codigo;

    @Column(name = "NOM_CLI", nullable = false)
    @NotNull(message = "Por favor, informe o nome.")
    private String nome;

    @Column(name = "DAT_NASC_CLI", nullable = false)
    @NotNull(message = "Por favor, informe a data de nascimento.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataNascimento;

    @Column(name = "SEX_CLI", nullable = false)
    @NotNull(message = "Por favor, informe o sexo.")
    private Character sexo;

    @Column(name = "QTD_VST", nullable = false)
    private Integer quantidadeVisitas;


    //Construtores
    public Cliente(){}

    public Cliente(Integer codigo, String nome, LocalDate dataNascimento, Character sexo, Integer quantidadeVisitas) {
        this.codigo = codigo;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.quantidadeVisitas = quantidadeVisitas;
    }

    //Getters e Setters
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Integer getQuantidadeVisitas() {
        return quantidadeVisitas;
    }

    public void setQuantidadeVisitas(Integer quantidadeVisitas) {
        this.quantidadeVisitas = quantidadeVisitas;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", sexo=" + sexo +
                '}';
    }
}
