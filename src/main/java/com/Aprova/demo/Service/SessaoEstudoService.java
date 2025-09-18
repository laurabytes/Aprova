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
import java.util.stream.Collectors;

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
        this.modelMapper = modelMapper;
    }


    public List<SessaoEstudoDTOResponse> listarSessaoEstudo() {
        List <SessaoEstudo> sessaoEstudos = sessaoEstudoRepository.listarSessaoEstudo();
        List<SessaoEstudoDTOResponse> Response = sessaoEstudos.stream()
                .map(sessaoEstudo -> modelMapper.map(sessaoEstudo, SessaoEstudoDTOResponse.class)).collect(Collectors.toList());
        return Response;
    }


    public SessaoEstudo listarSessaoEstudoId(Integer sessaoEstudoId) {
        return this.sessaoEstudoRepository.obterSessaoEstudoPorId(sessaoEstudoId);
    }

    public SessaoEstudoDTOResponse criarSessaoEstudo(SessaoEstudoDTORequest sessaoEstudoDTORequest) {

        SessaoEstudo sessaoEstudo = new SessaoEstudo();
        sessaoEstudo.setInicio(sessaoEstudoDTORequest.getInicio());
        sessaoEstudo.setFim(sessaoEstudoDTORequest.getFim());
        sessaoEstudo.setStatus(sessaoEstudoDTORequest.getStatus());
        sessaoEstudo.setMateria(materiaRepository.obterMateriaPorId(sessaoEstudoDTORequest.getIdMateria()));
        sessaoEstudo.setUsuario(usuarioRepository.obterUsuarioPorId(sessaoEstudoDTORequest.getIdUsuario()));
        SessaoEstudo sessaoEstudoSave = this.sessaoEstudoRepository.save(sessaoEstudo);
        return modelMapper.map(sessaoEstudoSave, SessaoEstudoDTOResponse.class);
    }


    public SessaoEstudoDTOResponse atualizarSessaoEstudo(Integer sessaoEstudoId, SessaoEstudoDTORequest request) {

        SessaoEstudo sessaoEstudo = this.listarSessaoEstudoId(sessaoEstudoId);
        if (sessaoEstudo == null){
            throw new IllegalArgumentException("Sessão estudo não existe");
        }
        sessaoEstudo.setInicio(request.getInicio());
        sessaoEstudo.setFim(request.getFim());
        sessaoEstudo.setStatus(request.getStatus());
        sessaoEstudo.setMateria(materiaRepository.obterMateriaPorId(request.getIdMateria()));
        sessaoEstudo.setUsuario(usuarioRepository.obterUsuarioPorId(request.getIdUsuario()));
        SessaoEstudo sessaoSalva = this.sessaoEstudoRepository.save(sessaoEstudo);
        return  modelMapper.map(sessaoSalva,SessaoEstudoDTOResponse.class);

    }

    public SessaoEstudoDTOUpdateResponse atualizarStatusSessaoEstudo(Integer sessaoEstudoId, SessaoEstudoDTOUpdateRequest sessaoEstudoDTOUpdateRequest) {
        SessaoEstudo sessaoEstudo = this.listarSessaoEstudoId(sessaoEstudoId);
        if (sessaoEstudo == null){
            throw new IllegalArgumentException("Sessão estudo não existe");
        }
        sessaoEstudo.setStatus(sessaoEstudoDTOUpdateRequest.getStatus());
        SessaoEstudo sessaoSalva = this.sessaoEstudoRepository.save(sessaoEstudo);
        return  modelMapper.map(sessaoSalva,SessaoEstudoDTOUpdateResponse.class);
    }

    public void apagarSessaoEstudo(Integer sessaoEstudoId){
        sessaoEstudoRepository.apagarSessaoEstudo(sessaoEstudoId);
    }
}