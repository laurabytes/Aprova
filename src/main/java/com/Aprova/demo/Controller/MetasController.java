package com.Aprova.demo.Controller;

import com.Aprova.demo.Entity.Metas;
import com.Aprova.demo.Service.MetasService;
import com.Aprova.demo.dto.request.MetasDTORequest;
import com.Aprova.demo.dto.response.MetasDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/metas")
public class MetasController {

//    @Autowired
//    private MetasService metasService;
//
//    @GetMapping("/listar")
//    public ResponseEntity<List<Metas>> listarMetas() {
//        return ResponseEntity.ok(metasService.listarMetas());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<MetasDTOResponse> getMetaById(@PathVariable Integer id) {
//        return ResponseEntity.ok(metasService.getMetaById(id));
//    }
//
//    @PostMapping("/criar")
//    public ResponseEntity<MetasDTOResponse> criarMeta(@RequestBody MetasDTORequest metasDTORequest) {
//        MetasDTOResponse response = metasService.criarMeta(metasDTORequest);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }
//
//    @PutMapping("/atualizar/{id}")
//    public ResponseEntity<MetasDTOResponse> atualizarMeta(@PathVariable Integer id, @RequestBody MetasDTORequest metasDTORequest) {
//        MetasDTOResponse response = metasService.atualizarMeta(id, metasDTORequest);
//        return ResponseEntity.ok(response);
//    }
//
//    @DeleteMapping("/apagar/{id}")
//    public ResponseEntity<Void> apagarMeta(@PathVariable Integer id) {
//        metasService.apagarMeta(id);
//        return ResponseEntity.noContent().build();
//    }
}