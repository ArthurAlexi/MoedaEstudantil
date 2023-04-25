package com.backend.controller;

import com.backend.dtos.UsuarioDTO;
import com.backend.service.AlunoService;
import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/login")
public class LoginController {

    private final AlunoService ALUNO_SERVICE;

    public LoginController(AlunoService alunoService) {
        this.ALUNO_SERVICE = alunoService;
    }

    @PostMapping("")
    public ResponseEntity<?> realizaLogin(@RequestBody JSONObject usuarioDTO){

        return ALUNO_SERVICE.realizaLogin(ALUNO_SERVICE.fabricaDTO(usuarioDTO));

    }

    @PostMapping("/{email}/{senha}")
    public ResponseEntity<?> realizaLogin(@PathVariable String email, @PathVariable String senha){

        return ALUNO_SERVICE.realizaLogin(new UsuarioDTO(email, senha));

    }

}
