package com.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_departamento")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    private Instituicao instituicao;

    public Departamento(String nome) {
        this.nome = nome;
        this.instituicao = new Instituicao("Inst.", null);
    }

    public Departamento(String nome, Instituicao instituicao) {
        this.nome = nome;
        this.instituicao = instituicao;
    }
}
