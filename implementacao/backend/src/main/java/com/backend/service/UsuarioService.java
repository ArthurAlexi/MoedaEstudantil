package com.backend.service;

import com.backend.dtos.UsuarioDTO;
import com.backend.model.Usuario;
import com.backend.repository.UsuarioRepository;
import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository USUARIO_REPOSITORY;

    public UsuarioService(UsuarioRepository USUARIO_REPOSITORY) {
        this.USUARIO_REPOSITORY = USUARIO_REPOSITORY;
    }

    public ResponseEntity<?> realizaLogin(UsuarioDTO usuarioDTO){

        Usuario usuario = USUARIO_REPOSITORY.realizaLogin(usuarioDTO.email(), usuarioDTO.senha());

        if(usuario == null){
            return ResponseEntity.badRequest().body("Email ou senha incorretos");
        }

        return ResponseEntity.ok(usuario);


    }

    /* Utilidades */

    public UsuarioDTO fabricaDTO(JSONObject json){

        return new UsuarioDTO(
                (String) json.get("email"),
                (String) json.get("senha")
        );

    }
}
