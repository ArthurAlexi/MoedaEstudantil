package com.backend.config;

import com.backend.model.*;
import com.backend.repository.*;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class Initialize {

    private final InstituicaoRepository INSTITUICAO_REPOSITORY;
    private final ProfessorRepository PROFESSOR_REPOSITORY;
    private final DepartamentoRepository DEPARTAMENTO_REPOSITORY;
    private final EmpresaRepository EMPRESA_REPOSITORY;
    private final CursoRepository CURSO_REPOSITORY;
    private final AlunoRepository ALUNO_REPOSITORY;

    public Initialize(ProfessorRepository professorRepository, InstituicaoRepository instituicaoRepository,
                      DepartamentoRepository departamentoRepository, EmpresaRepository empresaRepository,
                      CursoRepository cursoRepository, AlunoRepository alunoRepository){
        this.PROFESSOR_REPOSITORY = professorRepository;
        this.INSTITUICAO_REPOSITORY = instituicaoRepository;
        this.DEPARTAMENTO_REPOSITORY = departamentoRepository;
        this.EMPRESA_REPOSITORY = empresaRepository;
        this.CURSO_REPOSITORY = cursoRepository;
        this.ALUNO_REPOSITORY = alunoRepository;
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
                20L,
                "charles@gmail.com",
                "123456",
                "Charles",
                "12345678911",
                20.0,
                departamento
        ));

        Empresa empresa = new Empresa("emp", "123456", instituicao);

        EMPRESA_REPOSITORY.save(
                empresa
        );

        Curso curso =  new Curso(
                "Curso 1",
                instituicao
        );

        CURSO_REPOSITORY.save(curso);

        Aluno aluno = new Aluno(

                "email@aluno.com",
                "senhaAluno",
                "Aluno",
                "123456789",
                1.0,
                "123456",
                "rua x",
                curso
        );

        ALUNO_REPOSITORY.save(aluno);



    }

}
