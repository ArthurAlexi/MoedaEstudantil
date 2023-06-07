package com.backend.service;

import com.backend.model.Transacao;
import com.backend.repository.CupomRepository;
import com.backend.repository.TransacaoRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.backend.model.Cupom;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PDFService {

    private final CupomRepository cupomRepository;

    private final TransacaoRepository transacaoRepository;

    public PDFService(CupomRepository cupomRepository, TransacaoRepository transacaoRepository,
                      CupomService cupomService){
        this.cupomRepository = cupomRepository;
        this.transacaoRepository = transacaoRepository;
    }

    public ResponseEntity<?> geraPdfByCupom(Long id_cupom){

        Document document = new Document();

        Cupom cupom = getCupom(id_cupom);

        try {

            FileOutputStream outputStream = new FileOutputStream("cupom.pdf");

            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            document.open();

            escreveCupom(cupom, document);

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

    public ResponseEntity<?> geraPdfByTransacao(Long id_transacao){

        Document document = new Document();

        Transacao transacao = getTransacao(id_transacao);

        try {

            FileOutputStream outputStream = new FileOutputStream("transacao.pdf");

            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            document.open();

            escreveTransacao(transacao, document);

            document.close();

            File file = new File("transacao.pdf");

            file.deleteOnExit();

            return ResponseEntity.ok(Files.readAllBytes(Paths.get("transacao.pdf")));

        } catch (DocumentException | FileNotFoundException e) {
            throw new RuntimeException("Erro ao gerar pdf");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public ResponseEntity<?> geraPdfExtrato(Long id_aluno){

        List<Cupom> list_cupons = cupomRepository.retornaCuponsAluno(id_aluno);
        List<Transacao> list_transacoes = transacaoRepository.getTransacoesByAlunoId(id_aluno);

        Document document = new Document();

        try {

            FileOutputStream outputStream = new FileOutputStream("extrato.pdf");

            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            document.open();

            for(Cupom cupom : list_cupons){
                escreveCupom(cupom, document);
            }

            for(Transacao transacao : list_transacoes){
                escreveTransacao(transacao, document);
            }

            document.close();

            File file = new File("extrato.pdf");

            file.deleteOnExit();

            return ResponseEntity.ok(Files.readAllBytes(Paths.get("extrato.pdf")));

        } catch (DocumentException | FileNotFoundException e) {
            throw new RuntimeException("Erro ao gerar pdf");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public ResponseEntity<?> geraPdfExtratoProfessor(Long id_professor){

        List<Transacao> list_transacoes = transacaoRepository.getTransacoesByProfessorId(id_professor);

        Document document = new Document();

        try {

            FileOutputStream outputStream = new FileOutputStream("extrato.pdf");

            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            document.open();

            for(Transacao transacao : list_transacoes){
                escreveTransacao(transacao, document);
            }

            document.close();

            File file = new File("extrato.pdf");

            file.deleteOnExit();

            return ResponseEntity.ok(Files.readAllBytes(Paths.get("extrato.pdf")));

        } catch (DocumentException | FileNotFoundException e) {
            throw new RuntimeException("Erro ao gerar pdf");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    /* Comunicação JPA */

    public Cupom getCupom(Long id_cupom){

        Optional<Cupom> cupom = cupomRepository.findById(id_cupom);

        if(cupom.isEmpty()){
            throw new RuntimeException("Cupom não existe");
        }

        return cupom.get();
    }

    public Transacao getTransacao(Long id_transacao){

        Optional<Transacao> transacao = transacaoRepository.findById(id_transacao);

        if(transacao.isEmpty()){
            throw new RuntimeException("Transação não existe");
        }

        return transacao.get();

    }

    /* Utils */

    public void escreveCupom(Cupom cupom, Document document){

        try {
            document.add(new Paragraph("Nome do Aluno: " + cupom.getAluno().getNome()));
            document.add(new Paragraph("Valor da Transação: " + cupom.getVantagem().getValor()));
            document.add(new Paragraph("Descrição do cupom: " + cupom.getVantagem().getDescricao()));
            document.add(new Paragraph("Empresa: "
                    + cupom.getVantagem().getEmpresa().getNome()));
            document.add(new Paragraph("Data do resgate: " + cupom.getData_resgate()));
            document.add(new Paragraph("Código do resgate: " + cupom.getId()));
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }


    }

    public void escreveTransacao(Transacao transacao, Document document){

        try {
            document.add(new Paragraph("Nome do Aluno: " + transacao.getAluno().getNome()));
            document.add(new Paragraph("Valor da Transação: " + transacao.getValor()));
            document.add(new Paragraph("Professor: " + transacao.getProfessor().getNome()));
            document.add(new Paragraph("Data da transação: " + transacao.getData()));
            document.add(new Paragraph("Código da transação: " + transacao.getId()));
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

}
