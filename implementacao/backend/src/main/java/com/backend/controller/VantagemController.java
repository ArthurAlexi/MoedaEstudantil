package com.backend.controller;

import com.backend.dtos.VantagemDTO;
import com.backend.service.VantagemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vantagem/")
@CrossOrigin(origins = "http://localhost:4200")
public class VantagemController {

    private final VantagemService VANTAGEM_SERVICE;

    public VantagemController(VantagemService vantagemService) {
        this.VANTAGEM_SERVICE = vantagemService;
    }

    @PostMapping("criarVantagem")
    public ResponseEntity<?> criarVantagem(
            @RequestBody  VantagemDTO vantagem
    ){

        return VANTAGEM_SERVICE.criarVantagem(vantagem);

    }

    @GetMapping("retornaVantagemPorEmpresa/{idEmpresa}")
    public ResponseEntity<?> retornaVantagemPorEmpresa(
            @PathVariable  Long idEmpresa
    ){

        return VANTAGEM_SERVICE.retornaVantagemPorEmpresa(idEmpresa);

    }

    @GetMapping("retornaTodasVantagens")
    public ResponseEntity<?> retornaTodasVantagens(){
        return VANTAGEM_SERVICE.retornaTodasVantagens();
    }

}
