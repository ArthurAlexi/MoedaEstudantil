package com.backend.controller;

import com.backend.model.Empresa;
import com.backend.service.EmpresaService;
import net.minidev.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/empresa/")
public class EmpresaController {

    private final EmpresaService EMPRESA_SERVICE;

    public EmpresaController(EmpresaService empresaService) {
        this.EMPRESA_SERVICE = empresaService;
    }

    @PostMapping("insereEmpresa")
    public ResponseEntity<?> insereEmpresa(@RequestBody JSONObject empresa){

        Empresa empresa_enviar = EMPRESA_SERVICE.fabricaEmpresa(empresa);

        return EMPRESA_SERVICE.insereEmpresa(empresa_enviar);

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
    public ResponseEntity<?> alteraEmpresa(@RequestBody JSONObject empresa){

        Empresa empresa_enviar = EMPRESA_SERVICE.fabricaEmpresa(empresa);

        return EMPRESA_SERVICE.alteraEmpresa(empresa_enviar);
    }
}
