package com.backend.mapper;

import com.backend.dtos.VantagemDTO;
import com.backend.model.Empresa;
import com.backend.model.Vantagem;

public class VantagemMapper {

    public static Vantagem vantagemMapper(VantagemDTO vantagemDTO, Empresa empresa){

        return new Vantagem(

                vantagemDTO.descricao(),
                vantagemDTO.valor(),
                empresa

        );

    }

}
