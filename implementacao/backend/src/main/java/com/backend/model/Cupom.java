package com.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_cupom")
public class Cupom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date data_resgate;

    @OneToOne
    private Aluno aluno;

    @OneToOne
    private Vantagem vantagem;

    public Cupom(Aluno aluno, Vantagem vantagem) {
        this.data_resgate = new Date();
        this.aluno = aluno;
        this.vantagem = vantagem;
    }
}
