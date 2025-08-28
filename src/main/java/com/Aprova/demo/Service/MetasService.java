package com.Aprova.demo.Service;

import com.Aprova.demo.Entity.Metas;
import com.Aprova.demo.Repository.MetasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MetasService {

    private final MetasRepository metasRepository;

    public MetasService(MetasRepository metasRepository) {
        this.metasRepository = metasRepository;
    }

    public List<Metas> findAll() {
        return metasRepository.findAll();
    }

    public Optional<Metas> findById(Integer id) {
        return metasRepository.findById(id);
    }

    public Metas save(Metas metas) {
        return metasRepository.save(metas);
    }

    public void deleteById(Integer id) {
        metasRepository.deleteById(id);
    }

    public List<Metas> listarMetas(){
        return this.metasRepository.findAll();
    }
}