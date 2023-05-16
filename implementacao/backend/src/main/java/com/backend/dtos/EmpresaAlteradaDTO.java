package com.backend.dtos;

public record EmpresaAlteradaDTO(

        String email,
        String senha,
        String nome,
        String cnpj,
        Long id_empresa

) {
}
