package com.Aprova.demo.Controller;

import com.Aprova.demo.Entity.Materia;
import com.Aprova.demo.Entity.Metas;
import com.Aprova.demo.Service.MateriaService;
import com.Aprova.demo.Service.MetasService;
import com.Aprova.demo.dto.request.MateriaDTORequest;
import com.Aprova.demo.dto.request.MetasDTORequest;
import com.Aprova.demo.dto.response.MateriaDTOResponse;
import com.Aprova.demo.dto.response.MetasDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Meta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/metas")
public class MetasController {

    @Autowired
    private MetasService metasService;


    @GetMapping("/listar")
    public ResponseEntity<List<Metas>> listarMetas(){

        return ResponseEntity.ok(metasService.listarMetas());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Metas> listarMetasId(@PathVariable Integer id) {
        return ResponseEntity.ok(metasService.listarMetasId(id));
    }

    @PostMapping("/criar")
    public ResponseEntity<MetasDTOResponse> criarMetas(@RequestBody MetasDTORequest metasDTORequest) {
        MetasDTOResponse response = metasService.criarMetas(metasDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<MetasDTOResponse> atualizarMetas(@PathVariable Integer id, @RequestBody MetasDTORequest metasDTORequest) {
        MetasDTOResponse response = metasService.atualizarMetas(id, metasDTORequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<Void> apagarMetas(@PathVariable Integer id) {
        metasService.apagarMetas(id);
        return ResponseEntity.noContent().build();
    }
}