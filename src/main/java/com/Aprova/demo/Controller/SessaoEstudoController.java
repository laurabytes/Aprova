package com.Aprova.demo.Controller;

import com.Aprova.demo.Entity.SessaoEstudo;

import com.Aprova.demo.Service.SessaoEstudoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/sessaoestudo")
public class SessaoEstudoController {

    private SessaoEstudoService sessaoestudoService;

    public SessaoEstudoController(SessaoEstudoService sessaoestudoService) {
        this.sessaoestudoService = sessaoestudoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<SessaoEstudo>> listarSessaoEstudo(){
        return ResponseEntity.ok(sessaoestudoService.listarSessaoEstudo());
    }

}
