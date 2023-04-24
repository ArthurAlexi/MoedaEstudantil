package com.backend.service;

import com.backend.model.Aluno;
import com.backend.model.Curso;
import com.backend.model.Empresa;
import com.backend.model.Instituicao;
import com.backend.utils.Dictionary;
import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;


@Service
public class AlunoService {

    private final ServiceGeral SERVICE;

    public AlunoService(ServiceGeral serviceGeral) {
        this.SERVICE = serviceGeral;
    }

    public ResponseEntity<?> insereAluno(Aluno aluno){
        return SERVICE.insereObjeto(aluno, Dictionary.ALUNO);
    }

    public ResponseEntity<?> retornaTodosAlunos(){
        return SERVICE.retornaTodosObjetos(Dictionary.ALUNO);
    }

    public ResponseEntity<?> retornaAlunoPeloId(Long id){
        return SERVICE.retornaObjetoPeloId(id, Dictionary.ALUNO);
    }

    public ResponseEntity<?> deletaAlunoPeloId(Long id){
        return SERVICE.excluiObjeto(id, Dictionary.ALUNO);
    }

    public ResponseEntity<?> alteraAluno(Aluno aluno){
        return SERVICE.alteraObjeto(aluno, Dictionary.ALUNO);
    }


    /* Util */

    public Aluno fabricaAluno(JSONObject aluno){

        LinkedHashMap<?, ?> curso = ((LinkedHashMap<?, ?>) aluno.get("curso"));
        LinkedHashMap<?, ?> instituicao = (LinkedHashMap<?, ?>) curso.get("instituicao");

        return new Aluno(
                (String) aluno.get("id"),
                (String) aluno.get("email"),
                (String) aluno.get("senha"),
                (String) aluno.get("nome"),
                (String) aluno.get("cpf"),
                (String) aluno.get("creditos"),
                (String) aluno.get("rg"),
                (String) aluno.get("endereco"),
                new Curso(
                        (String) curso.get("id"),
                        (String) curso.get("nome"),
                            new Instituicao((String) instituicao.get("id"),
                                            (String) instituicao.get("nome"))

                )

        );

    }

}
