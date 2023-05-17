package com.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_vantagem")
public class Vantagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private String foto;

    private double valor;

    @ManyToOne
    private Empresa empresa;

    public Vantagem(Long id, String descricao, String foto, Double valor) {
        this.id = id;
        this.descricao = descricao;
        this.foto = foto;
        this.valor = valor;
    }

    public Vantagem(String descricao, Double valor, Empresa empresa) {
        this.descricao = descricao;
        this.valor = valor;
        this.empresa = empresa;
    }
}
