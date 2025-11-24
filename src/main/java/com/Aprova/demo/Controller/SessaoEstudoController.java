package com.Aprova.demo.Controller;

import com.Aprova.demo.Entity.Materia;
import com.Aprova.demo.Entity.SessaoEstudo;
import com.Aprova.demo.Service.MateriaService;
import com.Aprova.demo.Service.SessaoEstudoService;
import com.Aprova.demo.dto.request.MateriaDTORequest;
import com.Aprova.demo.dto.request.SessaoEstudoDTORequest;
import com.Aprova.demo.dto.response.MateriaDTOResponse;
import com.Aprova.demo.dto.response.SessaoEstudoDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/sessoes-estudo")
@CrossOrigin("*")
public class SessaoEstudoController {

    @Autowired
    private SessaoEstudoService sessaoEstudoService;


    @GetMapping("/listar")
    public ResponseEntity<List<SessaoEstudoDTOResponse>> listarSessaoEstudo(){

        return ResponseEntity.ok(sessaoEstudoService.listarSessaoEstudo());
    }


    @GetMapping("/{id}")
    public ResponseEntity<SessaoEstudo> listarSessaoEstudoId(@PathVariable Integer id) {
        return ResponseEntity.ok(sessaoEstudoService.listarSessaoEstudoId(id));
    }

    @PostMapping("/criar")
    public ResponseEntity<SessaoEstudoDTOResponse> criarSessaoEstudo(@RequestBody SessaoEstudoDTORequest sessaoEstudoDTORequest) {
        SessaoEstudoDTOResponse response = sessaoEstudoService.criarSessaoEstudo(sessaoEstudoDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<SessaoEstudoDTOResponse> atualizarSessaoEstudo(@PathVariable Integer id, @RequestBody SessaoEstudoDTORequest sessaoEstudoDTORequest) {
        SessaoEstudoDTOResponse response = sessaoEstudoService.atualizarSessaoEstudo(id, sessaoEstudoDTORequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<Void> apagarSessaoEstudo(@PathVariable Integer id) {
        sessaoEstudoService.apagarSessaoEstudo(id);
        return ResponseEntity.noContent().build();
    }
}
