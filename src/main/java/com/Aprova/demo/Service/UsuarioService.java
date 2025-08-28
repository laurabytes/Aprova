package com.Aprova.demo.Service;

import com.Aprova.demo.Entity.Materia;
import com.Aprova.demo.Entity.SessaoEstudo;
import com.Aprova.demo.Entity.Usuario;
import com.Aprova.demo.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Integer id) {
        usuarioRepository.deleteById(id);
    }
    public List<Usuario> listarUsuario(){
        return usuarioRepository.findAll();
    }
}