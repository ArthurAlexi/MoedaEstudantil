package com.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Empresa extends Usuario{


    private String nome;

    @JsonIgnore
    private String cnpj;

    @ManyToOne
    private Instituicao instituicao;

    /* Constructor */

    public Empresa(String email, String senha, String nome, String cnpj, Instituicao instituicao) {
        super(email, senha);
        this.nome = nome;
        this.cnpj = cnpj;
        this.instituicao = instituicao;
    }
}
