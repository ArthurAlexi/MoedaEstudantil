package com.backend.repository;

import com.backend.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    @Query(" SELECT T " +
            "FROM Transacao T " +
            "WHERE T.aluno.id = :id_aluno")
    List<Transacao> getTransacoesByAlunoId(
            @Param("id_aluno") Long id_aluno
    );

    @Query(" SELECT T " +
            "FROM Transacao T " +
            "WHERE T.professor.id = :id_professor")
    List<Transacao> getTransacoesByProfessorId(
            @Param("id_professor") Long id_professor
    );

}
