package com.backend.controller;

import com.backend.dtos.AlunoAlteradoDTO;
import com.backend.dtos.AlunoDTO;
import com.backend.service.AlunoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/aluno/")
@CrossOrigin(origins = "http://localhost:4200")
public class AlunoController {

    private final AlunoService ALUNO_SERVICE;

    public AlunoController(AlunoService alunoService) {
        this.ALUNO_SERVICE = alunoService;
    }

    @PostMapping("insereAluno")
    public ResponseEntity<?> insereAluno(@RequestBody AlunoDTO aluno){

        return ALUNO_SERVICE.insereAluno(aluno);

    }

    @GetMapping(value = "retornaTodosAlunos")
    public ResponseEntity<?> retornaTodosAlunos(){

        return ALUNO_SERVICE.retornaTodosAlunos();

    }

    @GetMapping(value = "retornaAlunoPeloId/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> retornaAlunoPeloId(@PathVariable Long id){

        return ALUNO_SERVICE.retornaAlunoPeloId(id);

    }

    @DeleteMapping(value = "deletaAlunoPeloId/{id}")
    public ResponseEntity<?> deletaAlunoPeloId(@PathVariable Long id){
        return ALUNO_SERVICE.deletaAlunoPeloId(id);
    }

    @PutMapping(value = "alteraAluno")
    public ResponseEntity<?> alteraAluno(@RequestBody AlunoAlteradoDTO alunoDto){

        return ALUNO_SERVICE.alteraAluno(alunoDto);
    }



}
