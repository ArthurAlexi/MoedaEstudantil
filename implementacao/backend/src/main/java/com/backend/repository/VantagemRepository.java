package com.backend.repository;

import com.backend.model.Vantagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public interface VantagemRepository extends JpaRepository<Vantagem, Long> {

    @Query( "SELECT V " +
            "FROM Vantagem V " +
            "INNER JOIN V.empresa E " +
            "ON E.id = V.empresa.id ")
    public LinkedList<Vantagem> retornaVantagensPelaEmpresa(@Param("id_empresa") Long id_empresa);

}
