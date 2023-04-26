package com.backend.controller;

import com.backend.dtos.UsuarioDTO;
import com.backend.service.UsuarioService;
import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login/")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    private final UsuarioService USUARIO_SERVICE;

    public LoginController(UsuarioService alunoService) {
        this.USUARIO_SERVICE = alunoService;
    }

    @PostMapping("")
    public ResponseEntity<?> realizaLogin(@RequestBody JSONObject usuarioDTO){

        return USUARIO_SERVICE.realizaLogin(USUARIO_SERVICE.fabricaDTO(usuarioDTO));

    }

    @PostMapping("/{email}/{senha}")
    public ResponseEntity<?> realizaLogin(@PathVariable String email, @PathVariable String senha){

        return USUARIO_SERVICE.realizaLogin(new UsuarioDTO(email, senha));

    }

}
