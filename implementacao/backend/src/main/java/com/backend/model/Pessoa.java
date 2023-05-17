package com.backend.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public abstract class Pessoa extends Usuario {

    private String nome;

    private String cpf;

    private double creditos;

    public Pessoa(String email, String senha, String nome, String cpf, double creditos) {
        super(email, senha);
        this.nome = nome;
        this.cpf = cpf;
        this.creditos = creditos;
    }

    public Pessoa(Long id, String email, String senha, String nome, String cpf, double creditos){
        super(id, email, senha);
        this.nome = nome;
        this.cpf = cpf;
        this.creditos = creditos;
    }
}
