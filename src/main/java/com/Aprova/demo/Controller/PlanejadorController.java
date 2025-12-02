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
@CrossOrigin(origins = "*")
public class PlanejadorController {

    @Autowired
    private PlanejadorService service;


    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PlanejadorDTOResponse>> listar(@PathVariable Integer usuarioId) {
        return ResponseEntity.ok(service.listarPorUsuario(usuarioId));
    }


    @PostMapping
    public ResponseEntity<PlanejadorDTOResponse> criar(@RequestBody PlanejadorDTORequest request) {
        return new ResponseEntity<>(service.criar(request), HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<PlanejadorDTOResponse> atualizar(@PathVariable Integer id, @RequestBody PlanejadorDTORequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }
}