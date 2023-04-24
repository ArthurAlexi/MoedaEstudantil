package com.backend.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public abstract class Pessoa extends Usuario {

    private String nome;

    private String cpf;

    private Double creditos;

    public Pessoa(String id, String email, String senha, String nome, String cpf, String creditos) {
        super(id, email, senha);
        this.nome = nome;
        this.cpf = cpf;

        if(creditos != null){
            this.creditos = Double.valueOf(creditos);
        }else{
            this.creditos = 0.0;
        }


    }
}
