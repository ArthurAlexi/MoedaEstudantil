package com.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Aluno extends Pessoa{

    private String rg;

    private String endereco;

    @ManyToOne
    private Curso curso;

    public Aluno(String email, String senha, String nome, String cpf, double creditos,
                 String rg, String endereco, Curso curso) {
        super(email, senha, nome, cpf, creditos);
        this.rg = rg;
        this.endereco = endereco;
        this.curso = curso;
    }
}
