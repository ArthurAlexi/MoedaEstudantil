package com.backend.service;

import com.backend.dtos.TransacaoDTO;
import com.backend.model.Aluno;
import com.backend.model.Professor;
import com.backend.model.Transacao;
import com.backend.repository.AlunoRepository;
import com.backend.repository.ProfessorRepository;
import com.backend.repository.TransacaoRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {

    private final AlunoRepository alunoRepository;

    private final ProfessorRepository professorRepository;

    private final TransacaoRepository transacaoRepository;

    private final MailService mailService;

    public TransacaoService(AlunoRepository alunoRepository, ProfessorRepository professorRepository,
                            TransacaoRepository transacaoRepository, MailService mailService) {
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
        this.transacaoRepository = transacaoRepository;
        this.mailService = mailService;
    }

    public ResponseEntity<?> realizaTransacao(TransacaoDTO transacaoDTO){

        Aluno aluno = alunoRepository.getReferenceById(transacaoDTO.id_aluno());
        Professor professor = professorRepository.getReferenceById(transacaoDTO.id_professor());

        if(aluno == null || professor == null){
            return new ResponseEntity<>("Professor ou Aluno não existem", HttpStatusCode.valueOf(400));
        }

        double creditos = professor.getCreditos();
        double creditos_transferidos = transacaoDTO.valor();
        double creditos_aluno = aluno.getCreditos();



        if(creditos < creditos_transferidos){
            return new ResponseEntity<>
                    ("Professor não possui crédito suficiente", HttpStatusCode.valueOf(400));
        }

        professor.setCreditos((creditos - creditos_transferidos));
        aluno.setCreditos(creditos_aluno + creditos_transferidos);

        Transacao transacao = new Transacao(professor, aluno, creditos_transferidos, transacaoDTO.data());

        transacaoRepository.save(transacao);
        professorRepository.saveAndFlush(professor);
        alunoRepository.saveAndFlush(aluno);

        mailService.sendMessage(professor.getEmail(), "Você acabou de realizar uma transação no valor de:" +
              transacao.getValor()  + " para o Aluno: " + aluno.getNome());

        mailService.sendMessage(aluno.getEmail(), "Você acabou de receber " + transacao.getValor() + " moedas" +
                "do professor: " + professor.getNome());

        return ResponseEntity.ok(transacao);

    }

    public ResponseEntity<?> retornaTodasTransacoes(){

        List<Transacao> transacaos = transacaoRepository.findAll();

        return ResponseEntity.ok(transacaos);

    }

}
