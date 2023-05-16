package com.backend.mapper;

import com.backend.dtos.EmpresaAlteradaDTO;
import com.backend.dtos.EmpresaDTO;
import com.backend.model.Empresa;
import com.backend.model.Instituicao;

public class EmpresaMapper {

    public static Empresa empresaMapper(EmpresaDTO empresaDTO, Instituicao instituicao){

        return new Empresa(

                empresaDTO.email(),
                empresaDTO.senha(),
                empresaDTO.nome(),
                empresaDTO.cnpj(),
                instituicao

        );

    }

    public static Empresa empresaMapper(EmpresaAlteradaDTO empresaAlteradaDTO, Empresa empresa){

        empresa.setCnpj(empresaAlteradaDTO.cnpj());
        empresa.setNome(empresaAlteradaDTO.nome());
        empresa.setSenha(empresaAlteradaDTO.senha());
        empresa.setEmail(empresaAlteradaDTO.email());

        return empresa;

    }

}
