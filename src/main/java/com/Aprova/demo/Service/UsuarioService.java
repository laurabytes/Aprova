package com.Aprova.demo.Service;

import com.Aprova.demo.Entity.Usuario;
import com.Aprova.demo.Repository.UsuarioRepository;
import com.Aprova.demo.dto.request.UsuarioDTORequest;
import com.Aprova.demo.dto.request.UsuarioDTOUpdateRequest;
import com.Aprova.demo.dto.response.UsuarioDTOResponse;
import com.Aprova.demo.dto.response.UsuarioDTOUpdateResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;



    public List<UsuarioDTOResponse> listarUsuario(){
        List<Usuario> usuarios = this.usuarioRepository.listarUsuarios();

        return usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTOResponse.class))
                .collect(Collectors.toList());
    }


    public Usuario listarUsuarioId(Integer usuarioId) {
        return this.usuarioRepository.obterUsuarioPorId(usuarioId);
    }

    public UsuarioDTOResponse criarUsuario(UsuarioDTORequest usuarioDTOrequest) {
        Usuario usuario = modelMapper.map(usuarioDTOrequest, Usuario.class);
        Usuario usuarioSave = this.usuarioRepository.save(usuario);
        return modelMapper.map(usuarioSave, UsuarioDTOResponse.class);
    }

    public UsuarioDTOResponse atualizarUsuario(Integer usuarioId, UsuarioDTORequest usuarioDTORequest) {
        Usuario usuario = this.listarUsuarioId(usuarioId);
        if (usuario != null){
            modelMapper.map(usuarioDTORequest,usuario);
            Usuario tempResponse = usuarioRepository.save(usuario);
            return modelMapper.map(tempResponse, UsuarioDTOResponse.class);
        } else {
            return null;
        }
    }

    public UsuarioDTOUpdateResponse atualizarStatusUsuario(Integer usuarioId, UsuarioDTOUpdateRequest usuarioDTOUpdateRequest) {
        Usuario usuario = this.listarUsuarioId(usuarioId);
        if (usuario != null) {
            usuario.setStatus(usuarioDTOUpdateRequest.getStatus());
            Usuario tempResponse = usuarioRepository.save(usuario);
            return modelMapper.map(tempResponse, UsuarioDTOUpdateResponse.class);
        } else {
            return null;
        }
    }

    public void apagarUsuario(Integer usuarioId){
        usuarioRepository.apagarUsuario(usuarioId);
    }
}