package com.Aprova.demo.Controller;


import com.Aprova.demo.Entity.Materia;
import com.Aprova.demo.Service.MateriaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/mater")
public class MateriaController {
    private MateriaService materiaService;

    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Materia>> listarMateria(){
        return ResponseEntity.ok(materiaService.listarMateria());
    }

}
