package com.Aprova.demo.Controller;

import com.Aprova.demo.Entity.Usuario;
import com.Aprova.demo.Service.UsuarioService;
import com.Aprova.demo.dto.request.UsuarioDTORequest;
import com.Aprova.demo.dto.response.UsuarioDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuarios") // Rota corrigida para "usuarios"
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Endpoint para LISTAR TODOS os usuários
    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        // Corrigindo o nome do método para corresponder ao service
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    // Endpoint para BUSCAR UM usuário pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTOResponse> getUsuarioById(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.getUsuarioById(id));
    }

    // Endpoint para CRIAR um novo usuário
    @PostMapping("/criar")
    public ResponseEntity<UsuarioDTOResponse> createUsuario(@RequestBody UsuarioDTORequest usuarioDTORequest) {
        UsuarioDTOResponse response = usuarioService.saveUsuario(usuarioDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Endpoint para ATUALIZAR um usuário existente
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<UsuarioDTOResponse> updateUsuario(@PathVariable Integer id, @RequestBody UsuarioDTORequest usuarioDTORequest) {
        UsuarioDTOResponse response = usuarioService.updateUsuario(id, usuarioDTORequest);
        return ResponseEntity.ok(response);
    }

    // Endpoint para APAGAR (logicamente) um usuário
    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}