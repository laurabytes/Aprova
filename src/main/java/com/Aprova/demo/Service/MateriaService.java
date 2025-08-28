package com.Aprova.demo.Service;

import com.Aprova.demo.Entity.Materia;
import com.Aprova.demo.Repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    public MateriaService(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    @GetMapping("/listar")
    public List<Materia> listarMateria() {
        return materiaRepository.findAll();
    }

    public Materia findById(Integer id) {
        return materiaRepository.findById(id).orElse(null);
    }

    public Materia save(Materia materia) {
        return materiaRepository.save(materia);
    }

    public void deleteById(Integer id) {
        materiaRepository.deleteById(id);
    }

}