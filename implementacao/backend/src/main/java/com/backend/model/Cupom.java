package com.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_cupom")
public class Cupom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Aluno aluno;

    @OneToOne
    private Vantagem vantagem;

    public Cupom(Aluno aluno, Vantagem vantagem) {
        this.aluno = aluno;
        this.vantagem = vantagem;
    }
}
