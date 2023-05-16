package com.backend.mapper;

import com.backend.dtos.AlunoAlteradoDTO;
import com.backend.dtos.AlunoDTO;
import com.backend.model.Aluno;
import com.backend.model.Curso;

public class AlunoMapper {

    public static Aluno alunoMapper(AlunoDTO alunoDTO, Curso curso){

        return new Aluno(

                alunoDTO.email(),
                alunoDTO.senha(),
                alunoDTO.nome(),
                alunoDTO.cpf(),
                alunoDTO.creditos(),
                alunoDTO.rg(),
                alunoDTO.endereco(),
                curso
        );

    }

    public static Aluno alunoMapper(AlunoAlteradoDTO alunoAlteradoDTO, Aluno aluno){

        aluno.setEmail(alunoAlteradoDTO.email());
        aluno.setSenha(alunoAlteradoDTO.senha());
        aluno.setNome(alunoAlteradoDTO.nome());
        aluno.setCpf(alunoAlteradoDTO.cpf());
        aluno.setCreditos(alunoAlteradoDTO.creditos());
        aluno.setRg(alunoAlteradoDTO.rg());
        aluno.setEndereco(alunoAlteradoDTO.endereco());

        return aluno;

    }

}
