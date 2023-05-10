package com.backend.config;

import com.backend.model.Departamento;
import com.backend.model.Instituicao;
import com.backend.model.Professor;
import com.backend.repository.DepartamentoRepository;
import com.backend.repository.InstituicaoRepository;
import com.backend.repository.ProfessorRepository;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class Initialize {

    private final InstituicaoRepository INSTITUICAO_REPOSITORY;
    private final ProfessorRepository PROFESSOR_REPOSITORY;
    private final DepartamentoRepository DEPARTAMENTO_REPOSITORY;


    public Initialize(ProfessorRepository professorRepository, InstituicaoRepository instituicaoRepository,
                      DepartamentoRepository departamentoRepository){
        this.PROFESSOR_REPOSITORY = professorRepository;
        this.INSTITUICAO_REPOSITORY = instituicaoRepository;
        this.DEPARTAMENTO_REPOSITORY = departamentoRepository;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void inicializaProfessor(){

        System.out.println("START");

        Instituicao instituicao = new Instituicao("Inst", null);

        INSTITUICAO_REPOSITORY.save(
                instituicao
        );

        Departamento departamento = new Departamento("Dep", instituicao);


        DEPARTAMENTO_REPOSITORY.save(
                departamento
        );

        PROFESSOR_REPOSITORY.save(new Professor(
                "20",
                "charles@gmail.com",
                "123456",
                "Charles",
                "12345678911",
                "20",
                departamento
        ));

    }

}
