package com.Aprova.demo.Controller;

import com.Aprova.demo.Entity.Metas;
import com.Aprova.demo.Entity.Usuario;
import com.Aprova.demo.Service.MetasService;
import com.Aprova.demo.Service.UsuarioService;
import com.Aprova.demo.dto.request.MetasDTORequest;
import com.Aprova.demo.dto.request.UsuarioDTORequest;
import com.Aprova.demo.dto.response.MetasDTOResponse;
import com.Aprova.demo.dto.response.UsuarioDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuario(){

        return ResponseEntity.ok(usuarioService.listarUsuario());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Usuario> listarUsuarioId(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.listarUsuarioId(id));
    }

    @PostMapping("/criar")
    public ResponseEntity<UsuarioDTOResponse> criarUsuario(@RequestBody UsuarioDTORequest UsuarioDTORequest) {
        UsuarioDTOResponse response = usuarioService.criarUsuario(UsuarioDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<UsuarioDTOResponse> atualizarUsuario(@PathVariable Integer id, @RequestBody UsuarioDTORequest UsuarioDTORequest) {
        UsuarioDTOResponse response = usuarioService.atualizarUsuario(id, UsuarioDTORequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<Void> apagarUsuario(@PathVariable Integer id) {
        usuarioService.apagarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}