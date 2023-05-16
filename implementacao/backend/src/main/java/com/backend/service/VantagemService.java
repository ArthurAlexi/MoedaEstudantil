package com.backend.service;

import com.backend.dtos.VantagemDTO;
import com.backend.mapper.VantagemMapper;
import com.backend.model.Empresa;
import com.backend.model.Vantagem;
import com.backend.repository.EmpresaRepository;
import com.backend.repository.VantagemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class VantagemService {

    private final VantagemRepository VANTAGEM_REPOSITORY;

    private final EmpresaRepository EMPRESA_REPOSITORY;


    public VantagemService(VantagemRepository vantagemRepository,
                           EmpresaRepository empresaRepository) {
        this.VANTAGEM_REPOSITORY = vantagemRepository;
        this.EMPRESA_REPOSITORY = empresaRepository;
    }

    public ResponseEntity<?> criarVantagem(VantagemDTO vantagemDTO){

        if(vantagemDTO == null){

            return ResponseEntity.badRequest().body("Vantagem está nula");

        }

        Empresa empresa = EMPRESA_REPOSITORY.getReferenceById(vantagemDTO.idEmpresa());

        if(empresa == null){

            return ResponseEntity.badRequest().body("Empresa não existe");

        }

        Vantagem vantagem = VantagemMapper.vantagemMapper(vantagemDTO, empresa);

        VANTAGEM_REPOSITORY.save(vantagem);

        return ResponseEntity.ok(vantagem);
    }

    public ResponseEntity<?> retornaVantagemPorEmpresa(Long id_empresa){

        Empresa empresa = EMPRESA_REPOSITORY.getReferenceById(id_empresa);

        if(empresa == null){
            return ResponseEntity.badRequest().body("Empresa não existe");
        }

        LinkedList<Vantagem> vantagens = VANTAGEM_REPOSITORY.retornaVantagensPelaEmpresa(id_empresa);

        return ResponseEntity.ok(vantagens);

    }
}
