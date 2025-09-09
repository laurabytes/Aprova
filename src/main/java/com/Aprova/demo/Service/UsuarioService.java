package com.Aprova.demo.Service;

import com.Aprova.demo.Entity.Usuario;
import com.Aprova.demo.Repository.UsuarioRepository;
import com.Aprova.demo.dto.request.UsuarioDTORequest;
import com.Aprova.demo.dto.response.UsuarioDTOResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.listarUsuarios();
    }

    public UsuarioDTOResponse getUsuarioById(Integer id) {
        Usuario usuario = usuarioRepository.obterUsuarioPorId(id);
        return modelMapper.map(usuario, UsuarioDTOResponse.class);
    }

    public UsuarioDTOResponse saveUsuario(UsuarioDTORequest usuarioDTORequest) {
        Usuario usuario = modelMapper.map(usuarioDTORequest, Usuario.class);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return modelMapper.map(savedUsuario, UsuarioDTOResponse.class);
    }

    public UsuarioDTOResponse updateUsuario(Integer id, UsuarioDTORequest usuarioDTORequest) {
        Usuario usuarioExistente = usuarioRepository.obterUsuarioPorId(id);
        if (usuarioExistente != null) {
            modelMapper.map(usuarioDTORequest, usuarioExistente);
            Usuario updatedUsuario = usuarioRepository.save(usuarioExistente);
            return modelMapper.map(updatedUsuario, UsuarioDTOResponse.class);
        } else {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
    }

    public void deleteUsuario(Integer id) {
        usuarioRepository.apagarUsuario(id);
    }
}