package com.backend.repository;

import com.backend.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    @Query(" SELECT A " +
            "FROM Aluno A " +
            "WHERE A.email = :email " +
            "AND A.senha = :senha")
    Aluno realizaLogin(@Param("email") String email, @Param("senha") String senha);

}
