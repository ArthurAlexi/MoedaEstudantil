package com.backend.config;

import com.backend.model.*;
import com.backend.repository.*;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.Properties;

@Configuration
public class Initialize {

    private final InstituicaoRepository INSTITUICAO_REPOSITORY;
    private final ProfessorRepository PROFESSOR_REPOSITORY;
    private final DepartamentoRepository DEPARTAMENTO_REPOSITORY;
    private final EmpresaRepository EMPRESA_REPOSITORY;
    private final CursoRepository CURSO_REPOSITORY;
    private final AlunoRepository ALUNO_REPOSITORY;
    private final CupomRepository CUPOM_REPOSITORY;
    private final VantagemRepository VANTAGEM_REPOSITORY;

    private static Properties properties;

    public Initialize(ProfessorRepository professorRepository, InstituicaoRepository instituicaoRepository,
                      DepartamentoRepository departamentoRepository, EmpresaRepository empresaRepository,
                      CursoRepository cursoRepository, AlunoRepository alunoRepository, CupomRepository cupomRepository,
                      VantagemRepository vantagemRepository){
        this.PROFESSOR_REPOSITORY = professorRepository;
        this.INSTITUICAO_REPOSITORY = instituicaoRepository;
        this.DEPARTAMENTO_REPOSITORY = departamentoRepository;
        this.EMPRESA_REPOSITORY = empresaRepository;
        this.CURSO_REPOSITORY = cursoRepository;
        this.ALUNO_REPOSITORY = alunoRepository;
        this.CUPOM_REPOSITORY = cupomRepository;
        this.VANTAGEM_REPOSITORY = vantagemRepository;
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

                "pabloaugustocm@gmail.com",
                "senhaAluno",
                "Aluno",
                "123456789",
                100.0,
                "123456",
                "rua x",
                curso
        );

        ALUNO_REPOSITORY.save(aluno);

        Vantagem vantagem = new Vantagem(
                "desc",
                1.0,
                "https://example.org",
                "name",
                empresa
        );

        VANTAGEM_REPOSITORY.save(vantagem);

        Cupom cupom = new Cupom(
                aluno,
                vantagem
        );

        CUPOM_REPOSITORY.save(cupom);



    }

    @EventListener(ApplicationStartedEvent.class)
    public void defineProperties(){

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp-mail.outlook.com");
        prop.put("mail.smtp.port", "587");

        properties = prop;

    }

    public static Session retornaSession(){

        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        "moeda_estudantil_teste@outlook.com",
                        "@MoedaEstudantil_LabTeste@"
                );
            }
        });

    }
}
