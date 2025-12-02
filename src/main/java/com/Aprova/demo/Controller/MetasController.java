package com.Aprova.demo.Controller;

import com.Aprova.demo.Entity.Metas;
import com.Aprova.demo.Service.MetasService;
import com.Aprova.demo.dto.request.MetasDTORequest;
import com.Aprova.demo.dto.request.MetasDTOUpdateRequest;
import com.Aprova.demo.dto.response.MetasDTOResponse;
import com.Aprova.demo.dto.response.MetasDTOUpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/metas")
@CrossOrigin("*")
public class MetasController {

    @Autowired
    private MetasService metasService;

    // CORREÇÃO: Lê o ?usuarioId=X da URL
    @GetMapping("/listar")
    public ResponseEntity<List<MetasDTOResponse>> listarMetas(@RequestParam Integer usuarioId){
        return ResponseEntity.ok(metasService.listarMetas(usuarioId));
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

    @PatchMapping("/atualizar-status/{id}")
    public ResponseEntity<MetasDTOUpdateResponse> atualizarStatusMetas(@PathVariable Integer id, @RequestBody MetasDTOUpdateRequest req) {
        return ResponseEntity.ok(metasService.atualizarStatusMetas(id, req));
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<Void> apagarMetas(@PathVariable Integer id) {
        metasService.apagarMetas(id);
        return ResponseEntity.noContent().build();
    }
}