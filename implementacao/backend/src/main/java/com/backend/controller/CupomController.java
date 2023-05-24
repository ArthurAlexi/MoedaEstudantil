package com.backend.controller;

import com.backend.dtos.CupomDTO;
import com.backend.service.CupomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/cupom/")
public class CupomController {

    private final CupomService cupomService;

    public CupomController(CupomService cupomService) {
        this.cupomService = cupomService;
    }

    @PostMapping("criarCupom")
    public ResponseEntity<?> criarCupom(
            @RequestBody CupomDTO cupomDTO
    ){

        return cupomService.criarCupom(cupomDTO);

    }

    @GetMapping("getCupomByAlunoID/{id}")
    public ResponseEntity<?> getCupom(
            @PathVariable Long id
    ){
        return cupomService.getCupomByAlunoID(id);
    }

}
