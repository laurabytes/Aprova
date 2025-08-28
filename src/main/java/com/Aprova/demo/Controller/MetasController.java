package com.Aprova.demo.Controller;

import com.Aprova.demo.Entity.Metas;

import com.Aprova.demo.Service.MetasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/metas")
public class MetasController {

    private MetasService metasService;

    public MetasController(MetasService metasService) {
        this.metasService = metasService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Metas>> listarMetas(){
        return ResponseEntity.ok(metasService.listarMetas());
    }

}
