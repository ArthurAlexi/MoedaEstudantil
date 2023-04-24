package com.backend.service;

import com.backend.model.Empresa;
import com.backend.model.Instituicao;
import com.backend.utils.Dictionary;
import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public class EmpresaService {

    private final ServiceGeral SERVICE;

    public EmpresaService(ServiceGeral serviceGeral) {
        this.SERVICE = serviceGeral;
    }

    public ResponseEntity<?> insereEmpresa(Empresa empresa){
        return SERVICE.insereObjeto(empresa, Dictionary.EMPRESA);
    }

    public ResponseEntity<?> retornaTodasEmpresas(){
        return SERVICE.retornaTodosObjetos(Dictionary.EMPRESA);
    }

    public ResponseEntity<?> retornaEmpresaPeloId(Long id){
        return SERVICE.retornaObjetoPeloId(id, Dictionary.EMPRESA);
    }

    public ResponseEntity<?> deletaEmpresaPeloId(Long id){
        return SERVICE.excluiObjeto(id, Dictionary.EMPRESA);
    }

    public ResponseEntity<?> alteraEmpresa(Empresa empresa){
        return SERVICE.alteraObjeto(empresa, Dictionary.EMPRESA);
    }

    /* UTIL */

    public Empresa fabricaEmpresa(JSONObject empresa){

        return new Empresa(
                (String) empresa.get("id"),
                (String) empresa.get("email"),
                (String) empresa.get("senha"),
                (String) empresa.get("nome"),
                (String) empresa.get("cnpj"),
                new Instituicao((String) ((LinkedHashMap<?, ?>)empresa.get("instituicao")).get("nome"),
                        (String) ((LinkedHashMap<?, ?>)empresa.get("instituicao")).get("id"))
        );

    }
}
