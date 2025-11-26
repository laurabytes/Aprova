package com.Aprova.demo.Controller;

import com.Aprova.demo.Entity.Usuario;
import com.Aprova.demo.Service.UsuarioService;
import com.Aprova.demo.dto.request.CreateUserDto;
import com.Aprova.demo.dto.request.LoginUserDto;
import com.Aprova.demo.dto.request.UsuarioDTORequest;
import com.Aprova.demo.dto.request.UsuarioDTOUpdateRequest;
import com.Aprova.demo.dto.response.RecoveryJwtTokenDto;
import com.Aprova.demo.dto.response.UsuarioDTOResponse;
import com.Aprova.demo.dto.response.UsuarioDTOUpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@RequestBody LoginUserDto loginUserDto) {
        RecoveryJwtTokenDto token = usuarioService.authenticateUser(loginUserDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/criar")
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDto createUserDto) {
        usuarioService.createUser(createUserDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioDTOResponse>> listarUsuario(){
        return ResponseEntity.ok(usuarioService.listarUsuario());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> listarUsuarioId(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.listarUsuarioId(id));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<UsuarioDTOResponse> atualizarUsuario(@PathVariable Integer id, @RequestBody UsuarioDTORequest usuarioDTORequest) {
        UsuarioDTOResponse response = usuarioService.atualizarUsuario(id, usuarioDTORequest);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/atualizar-status/{id}")
    public ResponseEntity<UsuarioDTOUpdateResponse> atualizarStatusUsuario(@PathVariable Integer id, @RequestBody UsuarioDTOUpdateRequest usuarioDTOUpdateRequest) {
        UsuarioDTOUpdateResponse response = usuarioService.atualizarStatusUsuario(id, usuarioDTOUpdateRequest);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<Void> apagarUsuario(@PathVariable Integer id) {
        usuarioService.apagarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}