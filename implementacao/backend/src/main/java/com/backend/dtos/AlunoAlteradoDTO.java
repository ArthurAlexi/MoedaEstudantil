package com.backend.dtos;

public record AlunoAlteradoDTO(

        Long id_aluno,
        String email,
        String senha,
        String nome,
        String cpf,
        Double creditos,
        String rg,
        String endereco

) {
}
