package com.Aprova.demo.Service;

import com.Aprova.demo.Entity.SessaoEstudo;
import com.Aprova.demo.Repository.SessaoEstudoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SessaoEstudoService {

    @Autowired
    private final SessaoEstudoRepository sessaoEstudoRepository;

    public SessaoEstudoService(SessaoEstudoRepository sessaoEstudoRepository) {
        this.sessaoEstudoRepository = sessaoEstudoRepository;
    }

    public List<SessaoEstudo> findAll() {
        return sessaoEstudoRepository.findAll();
    }

    public Optional<SessaoEstudo> findById(Integer id) {
        return sessaoEstudoRepository.findById(id);
    }

    public SessaoEstudo save(SessaoEstudo sessaoEstudo) {
        return sessaoEstudoRepository.save(sessaoEstudo);
    }

    public void deleteById(Integer id) {
        sessaoEstudoRepository.deleteById(id);
    }

    public List<SessaoEstudo> listarSessaoEstudo(){
        return this.sessaoEstudoRepository.findAll();
    }
}