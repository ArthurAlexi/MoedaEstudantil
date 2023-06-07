package com.backend.service;

import com.backend.repository.CupomRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import com.backend.model.Cupom;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PDFService {

    private final CupomRepository cupomRepository;

    public PDFService(CupomRepository cupomRepository){
        this.cupomRepository = cupomRepository;
    }

    public ResponseEntity<?> geraPdfByCupom(Long id_cupom){

        Document document = new Document();

        Cupom cupom = getCupom(id_cupom);

        try {

            FileOutputStream outputStream = new FileOutputStream("cupom.pdf");

            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            document.open();

            document.add(new Paragraph("Nome do Aluno: " + cupom.getAluno().getNome()));
            document.add(new Paragraph("Valor da Transação: " + cupom.getVantagem().getValor()));
            document.add(new Paragraph("Descrição do cupom: " + cupom.getVantagem().getDescricao()));
            document.add(new Paragraph("Empresa: "
                    + cupom.getVantagem().getEmpresa().getNome()));
            document.add(new Paragraph("Data do resgate: " + cupom.getData_resgate()));
            document.add(new Paragraph("Código do resgate: " + cupom.getId()));

            document.close();

            File file = new File("cupom.pdf");

            file.deleteOnExit();

            return ResponseEntity.ok(Files.readAllBytes(Paths.get("cupom.pdf")));

        } catch (DocumentException | FileNotFoundException e) {
            throw new RuntimeException("Erro ao gerar pdf");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Cupom getCupom(Long id_cupom){

        Optional<Cupom> cupom = cupomRepository.findById(id_cupom);

        if(cupom.isEmpty()){
            throw new RuntimeException("Cupom não existe");
        }

        return cupom.get();
    }

}
