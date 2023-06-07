package com.backend.repository;

import com.backend.model.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Long> {

    @Query(" SELECT C " +
            "FROM Cupom C " +
            "WHERE C.aluno.id = :id_aluno")
    List<Cupom> retornaCuponsAluno(
            @Param("id_aluno") Long id_aluno
    );
}
