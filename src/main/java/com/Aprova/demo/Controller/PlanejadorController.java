package com.Aprova.demo.Controller;

import com.Aprova.demo.Service.PlanejadorService;
import com.Aprova.demo.dto.request.PlanejadorDTORequest;
import com.Aprova.demo.dto.response.PlanejadorDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planejador")
@CrossOrigin(origins = "*") // Libera acesso para o Front
public class PlanejadorController {

    @Autowired
    private PlanejadorService service;

    // GET: Listar itens de um aluno
    // Exemplo de uso no front: api.get('/planejador/usuario/1')
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PlanejadorDTOResponse>> listar(@PathVariable Integer usuarioId) {
        return ResponseEntity.ok(service.listarPorUsuario(usuarioId));
    }

    // POST: Criar novo item
    // Exemplo de uso no front: api.post('/planejador', { dia: 'Segunda', hora: 14, ... })
    @PostMapping
    public ResponseEntity<PlanejadorDTOResponse> criar(@RequestBody PlanejadorDTORequest request) {
        return new ResponseEntity<>(service.criar(request), HttpStatus.CREATED);
    }

    // DELETE: Apagar item
    // Exemplo de uso no front: api.delete('/planejador/5')
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // PUT: Editar item
    @PutMapping("/{id}")
    public ResponseEntity<PlanejadorDTOResponse> atualizar(@PathVariable Integer id, @RequestBody PlanejadorDTORequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }
}