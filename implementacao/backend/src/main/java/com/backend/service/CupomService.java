package com.backend.service;

import com.backend.dtos.CupomDTO;
import com.backend.mapper.CupomMapper;
import com.backend.model.Aluno;
import com.backend.model.Cupom;
import com.backend.model.Vantagem;
import com.backend.repository.AlunoRepository;
import com.backend.repository.CupomRepository;
import com.backend.repository.VantagemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CupomService {

    private final CupomRepository cupomRepository;

    private final AlunoRepository alunoRepository;

    private final VantagemRepository vantagemRepository;

    private final MailService mailService;

    public CupomService(CupomRepository cupomRepository, AlunoRepository alunoRepository,
                        VantagemRepository vantagemRepository, MailService mailService) {
        this.cupomRepository = cupomRepository;
        this.alunoRepository = alunoRepository;
        this.vantagemRepository = vantagemRepository;
        this.mailService = mailService;
    }

    public ResponseEntity<?> criarCupom(CupomDTO cupomDTO){

        Aluno aluno = alunoRepository.getReferenceById(cupomDTO.idAluno());

        if(aluno == null){
            return ResponseEntity.badRequest().body("Aluno não existe");
        }

        Vantagem vantagem = vantagemRepository.getReferenceById(cupomDTO.idVantagem());

        if(vantagem == null){
            return ResponseEntity.badRequest().body("Vantagem não existe");
        }

        Cupom cupom = CupomMapper.mapperCupom(aluno, vantagem);

        double creditos_aluno = aluno.getCreditos();
        double valor_cupom = vantagem.getValor();


        if(creditos_aluno < valor_cupom){
            return ResponseEntity.badRequest().body("Aluno não possui créditos para o Cupom");
        }

        aluno.setCreditos((creditos_aluno - valor_cupom));

        cupomRepository.save(cupom);
        alunoRepository.saveAndFlush(aluno);

        mailService.sendMessage(aluno.getEmail(),
                "Você acabou de resgatar um cupom no valor de: " + valor_cupom);

        return ResponseEntity.ok(cupom);


    }

    public ResponseEntity<?> getCupomByAlunoID(Long id_aluno){

        Optional<Aluno> aluno = alunoRepository.findById(id_aluno);

        if(aluno.isEmpty()){
            return ResponseEntity.badRequest().body("Aluno não existe");
        }

        List<Cupom> cupons_aluno = cupomRepository.retornaCuponsAluno(id_aluno);

        return ResponseEntity.ok(cupons_aluno);
    }
}
