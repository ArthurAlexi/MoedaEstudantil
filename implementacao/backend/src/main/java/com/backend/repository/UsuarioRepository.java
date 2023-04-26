package com.backend.repository;

import com.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(" SELECT U " +
            "FROM Usuario U " +
            "WHERE U.email = :email " +
            "AND U.senha = :senha")
    Usuario realizaLogin(@Param("email") String email, @Param("senha") String senha);

}
