package com.Aprova.demo.Controller;

import com.Aprova.demo.Entity.Materia;
import com.Aprova.demo.Service.MateriaService;
import com.Aprova.demo.dto.request.MateriaDTORequest;
import com.Aprova.demo.dto.response.MateriaDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/materias")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;


    @GetMapping("/listar")
    public ResponseEntity<List<MateriaDTOResponse>> listarMaterias(){

        return ResponseEntity.ok(materiaService.listarMaterias());
    }


    @GetMapping("/{id}")
    public ResponseEntity<MateriaDTOResponse> listarMateriaId(@PathVariable Integer id) {
        return ResponseEntity.ok(materiaService.listarMateriaId(id));
    }

    @PostMapping("/criar")
    public ResponseEntity<MateriaDTOResponse> criarMateria(@RequestBody MateriaDTORequest materiaDTORequest) {
        MateriaDTOResponse response = materiaService.criarMateria(materiaDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<MateriaDTOResponse> atualizarMateria(@PathVariable Integer id, @RequestBody MateriaDTORequest materiaDTORequest) {
        MateriaDTOResponse response = materiaService.atualizarMateria(id, materiaDTORequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<Void> apagarMateria(@PathVariable Integer id) {
        materiaService.apagarMateria(id);
        return ResponseEntity.noContent().build();
    }
}