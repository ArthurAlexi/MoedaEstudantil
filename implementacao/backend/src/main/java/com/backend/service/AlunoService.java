package com.backend.service;

import com.backend.dtos.AlunoAlteradoDTO;
import com.backend.dtos.AlunoDTO;
import com.backend.mapper.AlunoMapper;
import com.backend.model.Aluno;
import com.backend.model.Curso;
import com.backend.repository.AlunoRepository;
import com.backend.repository.CursoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    private final CursoRepository cursoRepository;

    public AlunoService(AlunoRepository alunoRepository, CursoRepository cursoRepository) {
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
    }

    public ResponseEntity<?> insereAluno(AlunoDTO alunoDTO){

        Curso curso;

        if(alunoDTO.id_curso() != null){
            curso = cursoRepository.getReferenceById(alunoDTO.id_curso());
        }else{
            curso = cursoRepository.getReferenceById(1L);
        }

        Aluno aluno = AlunoMapper.alunoMapper(alunoDTO, curso);

        alunoRepository.save(aluno);

        return ResponseEntity.ok(aluno.getId());

    }

    public ResponseEntity<?> retornaTodosAlunos(){

        List<Aluno> alunos = alunoRepository.findAll();

        return ResponseEntity.ok(alunos);

    }

    public ResponseEntity<?> retornaAlunoPeloId(Long id){

        Optional<Aluno> aluno = alunoRepository.findById(id);

        if(aluno.isPresent()){
            return ResponseEntity.ok(aluno.get());
        }else{
            return ResponseEntity.badRequest().body("Aluno não existe");
        }
    }

    public ResponseEntity<?> deletaAlunoPeloId(Long id){

        Optional<Aluno> aluno = alunoRepository.findById(id);

        if(aluno.isPresent()){

            Aluno aluno_get = aluno.get();

            alunoRepository.delete(aluno_get);

            return ResponseEntity.ok(aluno_get);

        }else{
            return ResponseEntity.badRequest().body("Aluno não existe");
        }

    }

    public ResponseEntity<?> alteraAluno(AlunoAlteradoDTO alunoDTO){

        Optional<Aluno> aluno = alunoRepository.findById(alunoDTO.id_aluno());

        if(aluno.isEmpty()){
            return ResponseEntity.badRequest().body("Aluno não existe");
        }

        Aluno aluno_get = aluno.get();

        Aluno aluno_salvar = AlunoMapper.alunoMapper(alunoDTO, aluno_get);

        if(aluno_get.equals(aluno_salvar)){
            return ResponseEntity.badRequest().body("Não há mudanças no aluno");
        }

        alunoRepository.saveAndFlush(aluno_salvar);

        return ResponseEntity.ok(aluno_salvar.getId());

    }

}
