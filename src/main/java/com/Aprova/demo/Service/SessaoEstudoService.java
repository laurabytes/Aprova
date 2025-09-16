package com.Aprova.demo.Service;

import com.Aprova.demo.Entity.Materia;
import com.Aprova.demo.Entity.SessaoEstudo;
import com.Aprova.demo.Entity.Usuario;
import com.Aprova.demo.Repository.MateriaRepository;
import com.Aprova.demo.Repository.SessaoEstudoRepository;
import com.Aprova.demo.Repository.UsuarioRepository;
import com.Aprova.demo.dto.request.SessaoEstudoDTORequest;
import com.Aprova.demo.dto.request.SessaoEstudoDTOUpdateRequest;
import com.Aprova.demo.dto.response.SessaoEstudoDTOResponse;
import com.Aprova.demo.dto.response.SessaoEstudoDTOUpdateResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SessaoEstudoService {

    @Autowired
    private final SessaoEstudoRepository sessaoEstudoRepository;
    @Autowired
    private final MateriaRepository materiaRepository;

    private final UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    public SessaoEstudoService(SessaoEstudoRepository sessaoEstudoRepository, MateriaRepository materiaRepository, UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.sessaoEstudoRepository = sessaoEstudoRepository;
        this.usuarioRepository = usuarioRepository;
        this.materiaRepository = materiaRepository;
        this.modelMapper= modelMapper;
    }

    public List<SessaoEstudo> listarSessaoEstudo(){
        return this.sessaoEstudoRepository.listarSessaoEstudo();
    }

    public SessaoEstudo listarSessaoEstudoId(Integer sessaoEstudoId) {
        return this.sessaoEstudoRepository.obterSessaoEstudoPorId(sessaoEstudoId);
    }

    public SessaoEstudoDTOResponse criarSessaoEstudo(SessaoEstudoDTORequest sessaoEstudoDTORequest) {

        SessaoEstudo sessaoEstudo = modelMapper.map(sessaoEstudoDTORequest, SessaoEstudo.class);
        Materia materia = materiaRepository.obterMateriaPorId(sessaoEstudoDTORequest.getIdMateria());
        Usuario usuario = usuarioRepository.obterUsuarioPorId(sessaoEstudoDTORequest.getIdUsuario());

        sessaoEstudo.setUsuario(usuario);
        sessaoEstudo.setMateria(materia);
//        sessaoEstudo.setMateria(materiaRepository.obterMateriaPorId(sessaoEstudoDTORequest.getIdMateria()));
//        sessaoEstudo.setUsuario(usuarioRepository.obterUsuarioPorId(sessaoEstudoDTORequest.getIdUsuario()));
        SessaoEstudo sessaoEstudoSave = this.sessaoEstudoRepository.save(sessaoEstudo);
        SessaoEstudoDTOResponse sessaoEstudoDTOResponse = modelMapper.map(sessaoEstudoSave, SessaoEstudoDTOResponse.class);
        return sessaoEstudoDTOResponse;
    }

    public SessaoEstudoDTOResponse atualizarSessaoEstudo(Integer sessaoEstudoId, SessaoEstudoDTORequest sessaoEstudoDTORequest) {
        //antes de atualizar busca se existe o registro a ser atualizar
        SessaoEstudo sessaoEstudo = this.listarSessaoEstudoId(sessaoEstudoId);

        //se encontra o registro a ser atualizado
        if (sessaoEstudo != null){
            //copia os dados a serem atualizados do DTO de entrada para um objeto do tipo participante
            //que é compatível com o repository para atualizar
            modelMapper.map(sessaoEstudoDTORequest,sessaoEstudo);

            //com o objeto no formato correto tipo "participante" o comando "save" salva
            // no banco de dados o objeto atualizado
            SessaoEstudo tempResponse = sessaoEstudoRepository.save(sessaoEstudo);

            return modelMapper.map(tempResponse, SessaoEstudoDTOResponse.class);
        }else {
            return null;
        }

    }

    public SessaoEstudoDTOUpdateResponse atualizarStatusSessaoEstudo(Integer sessaoEstudoId, SessaoEstudoDTOUpdateRequest sessaoEstudoDTOUpdateRequest) {
        //antes de atualizar busca se existe o registro a ser atualizar
        SessaoEstudo sessaoEstudo = this.listarSessaoEstudoId(sessaoEstudoId);

        //se encontra o registro a ser atualizado
        if (sessaoEstudo != null) {
            //atualizamos unicamente o campo de status
            sessaoEstudo.setStatus(sessaoEstudoDTOUpdateRequest.getStatus());

            //com o objeto no formato correto tipo "participante" o comando "save" salva
            // no banco de dados o objeto atualizado
            SessaoEstudo tempResponse = sessaoEstudoRepository.save(sessaoEstudo);
            return modelMapper.map(tempResponse, SessaoEstudoDTOUpdateResponse.class);
        }
        else{
            return null;
        }
    }

    public void apagarSessaoEstudo(Integer sessaoEstudoId){
        sessaoEstudoRepository.apagarSessaoEstudo(sessaoEstudoId);
    }
}