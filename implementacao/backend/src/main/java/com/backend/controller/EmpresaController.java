package com.backend.controller;

import com.backend.dtos.EmpresaAlteradaDTO;
import com.backend.dtos.EmpresaDTO;
import com.backend.service.EmpresaService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/empresa/")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpresaController {

    private final EmpresaService EMPRESA_SERVICE;

    public EmpresaController(EmpresaService empresaService) {
        this.EMPRESA_SERVICE = empresaService;
    }

    @PostMapping("insereEmpresa")
    public ResponseEntity<?> insereEmpresa(@RequestBody EmpresaDTO empresa){

        return EMPRESA_SERVICE.insereEmpresa(empresa);

    }

    @GetMapping(value = "retornaTodasEmpresas")
    public ResponseEntity<?> retornaTodasEmpresas(){

        return EMPRESA_SERVICE.retornaTodasEmpresas();

    }

    @GetMapping(value = "retornaEmpresaPeloId/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> retornaEmpresaPeloId(@PathVariable Long id){

        return EMPRESA_SERVICE.retornaEmpresaPeloId(id);

    }

    @DeleteMapping(value = "deletaEmpresaPeloId/{id}")
    public ResponseEntity<?> deletaEmpresaPeloId(@PathVariable Long id){

        return EMPRESA_SERVICE.deletaEmpresaPeloId(id);
    }

    @PutMapping(value = "alteraEmpresa")
    public ResponseEntity<?> alteraEmpresa(@RequestBody EmpresaAlteradaDTO empresa){

        return EMPRESA_SERVICE.alteraEmpresa(empresa);
    }

}
