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

    @GetMapping(value = "baixarPdf/{idCupom}",
    produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> baixarPdf(
            @PathVariable Long idCupom
    ){

        return PDF_SERVICE.geraPdfByCupom(idCupom);

    }

}
