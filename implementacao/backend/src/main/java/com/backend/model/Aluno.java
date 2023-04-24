package com.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Aluno extends Pessoa{

    private String rg;

    private String endereco;

    @ManyToOne
    private Instituicao instituicao;

    @ManyToOne
    private Curso curso;

}
