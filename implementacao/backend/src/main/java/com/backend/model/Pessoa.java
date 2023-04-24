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

}
