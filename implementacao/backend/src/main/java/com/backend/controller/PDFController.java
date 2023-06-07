package com.backend.controller;

import com.backend.service.PDFService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/pdf/")
@CrossOrigin(origins = "http://localhost:4200")
public class PDFController {

    private final PDFService PDF_SERVICE;

    public PDFController(PDFService pdfService){
        this.PDF_SERVICE = pdfService;

    }

    @GetMapping(value = "baixarPdfCupom/{idCupom}",
    produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> baixarPdfCupom(
            @PathVariable Long idCupom
    ){

        return PDF_SERVICE.geraPdfByCupom(idCupom);

    }

    @GetMapping(value = "baixarPdfTransacao/{idTransacao}",
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> baixarPdfTransacao(
            @PathVariable Long idTransacao
    ){

        return PDF_SERVICE.geraPdfByTransacao(idTransacao);

    }

    @GetMapping(value = "baixarExtratoAluno/{idAluno}",
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> gerarExtrato(
            @PathVariable Long idAluno
    ){

        return PDF_SERVICE.geraPdfExtrato(idAluno);

    }

    @GetMapping(value = "baixarExtratoProfessor/{idProfessor}",
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> gerarExtratoProfessor(
            @PathVariable Long idProfessor
    ){

        return PDF_SERVICE.geraPdfExtrato(idProfessor);

    }

}
