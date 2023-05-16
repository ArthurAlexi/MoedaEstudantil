package com.backend.service;

import com.backend.dtos.EmpresaAlteradaDTO;
import com.backend.dtos.EmpresaDTO;
import com.backend.mapper.EmpresaMapper;
import com.backend.model.Empresa;
import com.backend.model.Instituicao;
import com.backend.repository.EmpresaRepository;
import com.backend.repository.InstituicaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    private final InstituicaoRepository instituicaoRepository;

    public EmpresaService(EmpresaRepository empresaRepository, InstituicaoRepository instituicaoRepository) {
        this.empresaRepository = empresaRepository;
        this.instituicaoRepository = instituicaoRepository;
    }

    public ResponseEntity<?> insereEmpresa(EmpresaDTO empresaDTO){

        Instituicao instituicao;

        if(empresaDTO.id_instituicao() != null){
            instituicao = instituicaoRepository.getReferenceById(empresaDTO.id_instituicao());
        }else{
            instituicao = instituicaoRepository.getReferenceById(1L);
        }

        Empresa empresa = EmpresaMapper.empresaMapper(empresaDTO, instituicao);

        empresaRepository.save(empresa);

        return ResponseEntity.ok(empresa);

    }

    public ResponseEntity<?> retornaTodasEmpresas(){

        List<Empresa> empresas = empresaRepository.findAll();

        return ResponseEntity.ok(empresas);

    }

    public ResponseEntity<?> retornaEmpresaPeloId(Long id){

        Optional<Empresa> empresa = empresaRepository.findById(id);

        if(empresa.isPresent()){
            return ResponseEntity.ok(empresa.get());
        }else{
            return ResponseEntity.badRequest().body("Empresa não existe");
        }

    }

    public ResponseEntity<?> deletaEmpresaPeloId(Long id){

        Optional<Empresa> empresa = empresaRepository.findById(id);

        if(empresa.isPresent()){

            Empresa aluno_get = empresa.get();

            empresaRepository.delete(aluno_get);

            return ResponseEntity.ok(aluno_get);

        }else{
            return ResponseEntity.badRequest().body("Aluno não existe");
        }

    }

    public ResponseEntity<?> alteraEmpresa(EmpresaAlteradaDTO empresaAlteradaDTO){

        Optional<Empresa> empresa = empresaRepository.findById(empresaAlteradaDTO.id_empresa());

        if(empresa.isEmpty()){
            return ResponseEntity.badRequest().body("Empresa não existe");
        }

        Empresa empresa_get = empresa.get();

        Empresa empresa_salvar = EmpresaMapper.empresaMapper(empresaAlteradaDTO, empresa_get);

        if(empresa_get.equals(empresa_salvar)){
            return ResponseEntity.badRequest().body("Não há mudanças no aluno");
        }

        empresaRepository.saveAndFlush(empresa_salvar);

        return ResponseEntity.ok(empresa_salvar.getId());

    }
}
