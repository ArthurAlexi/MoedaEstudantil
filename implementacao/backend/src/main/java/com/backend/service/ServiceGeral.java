package com.backend.service;


import com.backend.model.Aluno;
import com.backend.model.Curso;
import com.backend.model.Empresa;
import com.backend.model.Instituicao;
import com.backend.repository.AlunoRepository;
import com.backend.repository.CursoRepository;
import com.backend.repository.EmpresaRepository;
import com.backend.repository.InstituicaoRepository;
import com.backend.utils.Dictionary;
import jakarta.transaction.Transactional;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.annotation.Transient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceGeral {

    private final HashMap<String, Object> REPOSITORYS = new HashMap<>();

    private final EmpresaRepository EMPRESA_REPOSITORY;

    private final InstituicaoRepository INSTITUICAO_REPOSITORY;

    private final AlunoRepository ALUNO_REPOSITORY;

    private final CursoRepository CURSO_REPOSITORY;


    /* Constructor */

    public ServiceGeral(EmpresaRepository empresaRepository, InstituicaoRepository instituicaoRepository,
                        AlunoRepository alunoRepository, CursoRepository cursoRepository) {
        this.EMPRESA_REPOSITORY = empresaRepository;
        this.INSTITUICAO_REPOSITORY = instituicaoRepository;
        this.ALUNO_REPOSITORY = alunoRepository;
        this.CURSO_REPOSITORY = cursoRepository;
    }

    /* Métodos gerais */

    public ResponseEntity<?> insereObjeto(Object objeto, String repository){

        if(objetoIsNulo(objeto)){
            return ResponseEntity.badRequest().body("Objeto está nulo");
        }

        switch (repository){

            case Dictionary.EMPRESA -> {

                if( ((Empresa) objeto).getInstituicao() == null){
                    return ResponseEntity.badRequest().body("Instituicao está nula");
                }

                if(((Empresa) objeto).getInstituicao().getId() == null){
                    ((InstituicaoRepository)REPOSITORYS.get
                            (Dictionary.INSTITUICAO)).save(((Empresa) objeto).getInstituicao());
                }else{

                    Instituicao instituicao = ((InstituicaoRepository)REPOSITORYS.get
                            (Dictionary.INSTITUICAO)).getReferenceById(((Empresa) objeto).getInstituicao().getId());

                    if(instituicao != null){
                        ((Empresa) objeto).setInstituicao(instituicao);
                    }else{
                        return ResponseEntity.badRequest().body("Instituição não existe");
                    }
                }

                ((EmpresaRepository)REPOSITORYS.get(repository)).save((Empresa) objeto);

            }

            case Dictionary.ALUNO -> {

                Curso curso = ((Aluno) objeto).getCurso();
                Instituicao instituicao = curso.getInstituicao();

                if(curso == null){
                    return ResponseEntity.badRequest().body("Curso está nulo");
                }

                if(instituicao == null){
                    return ResponseEntity.badRequest().body("Instituição está nulo");
                }

                if(curso.getId() == null){
                    ((CursoRepository)REPOSITORYS.get
                            (Dictionary.CURSO)).save(curso);
                }else{

                    Curso curso_add = ((CursoRepository)REPOSITORYS.get
                            (Dictionary.CURSO)).getReferenceById(curso.getId());

                    if(curso_add == null){
                        return ResponseEntity.badRequest().body("Curso não existe");
                    }

                    ((Aluno) objeto).setCurso(curso_add);

                    if(instituicao.getId() == null){
                        ((InstituicaoRepository)REPOSITORYS.get
                                (Dictionary.INSTITUICAO)).save(instituicao);
                    }else {

                        Instituicao instituicao_add = ((InstituicaoRepository) REPOSITORYS.get
                                (Dictionary.INSTITUICAO)).getReferenceById(instituicao.getId());

                        if (instituicao_add == null) {
                            return ResponseEntity.badRequest().body("Instituição não existe");
                        }

                        ((Aluno) objeto).getCurso().setInstituicao(instituicao_add);
                    }
                }

                ((AlunoRepository)REPOSITORYS.get(repository)).save((Aluno) objeto);

            }

            default -> System.out.println("Erro ao inserir o objeto: " + objeto.getClass());
        }

        return ResponseEntity.ok("Objeto inserido com sucesso");

    }

    @Transactional
    public ResponseEntity<?> retornaTodosObjetos(String repository){

        switch (repository){

            case Dictionary.EMPRESA -> {
                List<Empresa> empresas = ((EmpresaRepository)REPOSITORYS.get(repository)).findAll();
                return ResponseEntity.ok(empresas);
            }

            case Dictionary.ALUNO -> {
                List<Aluno> alunos = ((AlunoRepository)REPOSITORYS.get(repository)).findAll();
                return ResponseEntity.ok(alunos);
            }

            default -> System.out.println("Repositório não existe: " + repository);
        }

        return ResponseEntity.badRequest().body("Repositório não existe: " + repository);

    }

    @Transactional
    public ResponseEntity<?> retornaObjetoPeloId(Long id, String repository){

        if(objetoIsNulo(id)){
            return ResponseEntity.badRequest().body("Id está nulo");
        }

        Optional<?> objeto_procura = Optional.empty();

        switch (repository){
            case Dictionary.EMPRESA -> objeto_procura = ((EmpresaRepository)REPOSITORYS.get(repository)).findById(id);
            case Dictionary.ALUNO -> objeto_procura = ((AlunoRepository)REPOSITORYS.get(repository)).findById(id);
            default -> System.out.println("Erro ao achar o objeto de id: " + id);
        }

        return verificaObjeto(objeto_procura);

    }

    @Transactional
    public ResponseEntity<?> excluiObjeto(Long id, String repository){

        if(objetoIsNulo(id)){
            return ResponseEntity.badRequest().body("Id está nulo");
        }

        switch (repository){
            case Dictionary.EMPRESA -> ((EmpresaRepository)REPOSITORYS.get(repository)).deleteById(id);
            case Dictionary.ALUNO -> ((AlunoRepository)REPOSITORYS.get(repository)).deleteById(id);
            default -> System.out.println("Erro ao achar o objeto de id: " + id);
        }

        return ResponseEntity.ok("Objeto excluído");


    }

    @Transactional
    public ResponseEntity<?> alteraObjeto(Object objeto, String repository){

        if(objetoIsNulo(objeto)){
            return ResponseEntity.badRequest().body("Objeto está nulo");
        }

        Optional<?> objeto_procura;

        switch (repository){

            case Dictionary.EMPRESA -> {

                objeto_procura = ((EmpresaRepository)REPOSITORYS.get(repository)).findById(((Empresa) objeto).getId());

                if(objeto_procura.isEmpty()){
                    return ResponseEntity.badRequest().body("Objeto não existe");
                }

                ((EmpresaRepository)REPOSITORYS.get(repository)).saveAndFlush((Empresa) objeto_procura.get());

            }

            case Dictionary.ALUNO -> {

                objeto_procura = ((AlunoRepository)REPOSITORYS.get(repository)).findById(((Aluno) objeto).getId());

                if(objeto_procura.isEmpty()){
                    return ResponseEntity.badRequest().body("Objeto não existe");
                }

                ((AlunoRepository)REPOSITORYS.get(repository)).saveAndFlush((Aluno) objeto_procura.get());

            }


            default -> System.out.println("Erro ao atualizar objeto do tipo: " + repository);

        }

        return ResponseEntity.ok("Objeto atualizado com sucesso");



    }

    /* Utilidades */

    private static boolean objetoIsNulo(Object objeto){

        return objeto == null;
    }

    private static ResponseEntity<?> verificaObjeto(Optional<?> objeto_procura){

        if(objeto_procura.isEmpty()){
            return ResponseEntity.badRequest().body("Objeto não existe");
        }

        return ResponseEntity.ok(objeto_procura.get());
    }


    /* Configuração */

    @EventListener(ApplicationStartedEvent.class)
    public void inicializaRepositorys(){

        REPOSITORYS.put(Dictionary.EMPRESA, this.EMPRESA_REPOSITORY);
        REPOSITORYS.put(Dictionary.INSTITUICAO, this.INSTITUICAO_REPOSITORY);
        REPOSITORYS.put(Dictionary.ALUNO, this.ALUNO_REPOSITORY);
        REPOSITORYS.put(Dictionary.CURSO, this.CURSO_REPOSITORY);

    }


}
