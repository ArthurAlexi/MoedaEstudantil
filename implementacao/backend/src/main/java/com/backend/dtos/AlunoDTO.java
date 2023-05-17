package com.backend.dtos;

public record AlunoDTO(
        String email,
        String senha,
        String nome,
        String cpf,
        double creditos,
        String rg,
        String endereco,
        Long id_curso
) {
}
