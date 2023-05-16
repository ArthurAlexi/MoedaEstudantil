package com.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Professor extends Pessoa{

    @ManyToOne
    private Departamento departamento;

    public Professor(String nome, String cpf, Double creditos) {
        super(nome, cpf, creditos);
        this.departamento = new Departamento("Dep");
    }

    public Professor(Long id, String email, String senha, String nome, String cpf, Double creditos, Departamento departamento) {
        super(id, email, senha, nome, cpf, creditos);
        this.departamento = departamento;
    }
}
