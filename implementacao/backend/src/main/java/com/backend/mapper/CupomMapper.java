package com.backend.mapper;

import com.backend.model.Aluno;
import com.backend.model.Cupom;
import com.backend.model.Vantagem;

public class CupomMapper {

    public static Cupom mapperCupom(Aluno aluno, Vantagem vantagem){

        return new Cupom(
            aluno,
            vantagem
        );

    }

}
