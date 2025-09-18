package com.Aprova.demo.Service;

import com.Aprova.demo.Entity.Materia;
import com.Aprova.demo.Entity.Metas;
import com.Aprova.demo.Entity.SessaoEstudo;
import com.Aprova.demo.Repository.MateriaRepository;
import com.Aprova.demo.Repository.UsuarioRepository;
import com.Aprova.demo.dto.request.MateriaDTORequest;
import com.Aprova.demo.dto.request.MateriaDTOUpdateRequest;
import com.Aprova.demo.dto.response.MateriaDTOResponse;
import com.Aprova.demo.dto.response.MateriaDTOUpdateResponse;
import com.Aprova.demo.dto.response.MetasDTOResponse;
import com.Aprova.demo.dto.response.SessaoEstudoDTOResponse;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MateriaService {

    private final MateriaRepository materiaRepository;

    private final UsuarioRepository usuarioRepository;
    @Autowired
    private ModelMapper modelMapper;

    public MateriaService(MateriaRepository materiaRepository, UsuarioRepository usuarioRepository) {
        this.materiaRepository = materiaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<MateriaDTOResponse> listarMaterias(){
        List<Materia> materias = materiaRepository.findAll();
        List<MateriaDTOResponse> responses = materias.stream()
                .map(materia -> modelMapper.map(materia, MateriaDTOResponse.class))
                .collect(Collectors.toList());
        return responses;
    }

    public MateriaDTOResponse listarMateriaId(Integer materiaId) {
        return modelMapper.map(materiaRepository.obterMateriaPorId(materiaId), MateriaDTOResponse.class);
    }

    public MateriaDTOResponse criarMateria(MateriaDTORequest materiaDTOrequest) {

        Materia materia = new Materia();
        materia.setStatus(materiaDTOrequest.getStatus());
        materia.setCor(materiaDTOrequest.getCor());
        materia.setNome(materiaDTOrequest.getNome());
        materia.setPrioridade(materiaDTOrequest.getPrioridade());
        materia.setUsuario(usuarioRepository.obterUsuarioPorId(materiaDTOrequest.getUsuarioId()));
        Materia materiaSave = this.materiaRepository.save(materia);
        MateriaDTOResponse materiaDTOResponse = modelMapper.map(materiaSave, MateriaDTOResponse.class);
        return materiaDTOResponse;
    }

    public MateriaDTOResponse atualizarMateria(@Valid Integer materiaId, MateriaDTORequest materiaDTORequest) {
        MateriaDTOResponse materia = this.listarMateriaId(materiaId);
        if (materia != null) {
            materia.setNome(materiaDTORequest.getNome());
            materia.setCor(materiaDTORequest.getCor());
            materia.setPrioridade(materiaDTORequest.getPrioridade());
            materia.setStatus(materiaDTORequest.getStatus());
            materia.setUsuario(usuarioRepository.obterUsuarioPorId(materiaDTORequest.getUsuarioId()));
            Materia materiaTemp = materiaRepository.save(materia);

            return modelMapper.map(materiaTemp, MateriaDTOResponse.class);
        } else {
            return null;
        }


    }

    public void apagarMateria(Integer materiaId){
        materiaRepository.apagadoLogicoMateria(materiaId);
    }
}