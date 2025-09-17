package com.Aprova.demo.Service;

import com.Aprova.demo.Entity.Materia;
import com.Aprova.demo.Repository.MateriaRepository;
import com.Aprova.demo.dto.request.MateriaDTORequest;
import com.Aprova.demo.dto.request.MateriaDTOUpdateRequest;
import com.Aprova.demo.dto.response.MateriaDTOResponse;
import com.Aprova.demo.dto.response.MateriaDTOUpdateResponse;
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
    @Autowired
    private ModelMapper modelMapper;

    public MateriaService(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
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

        Materia materia = modelMapper.map(materiaDTOrequest, Materia.class);
        Materia materiaSave = this.materiaRepository.save(materia);
        MateriaDTOResponse materiaDTOResponse = modelMapper.map(materiaSave, MateriaDTOResponse.class);
        return materiaDTOResponse;
    }

    public MateriaDTOResponse atualizarMateria(Integer materiaId, MateriaDTORequest materiaDTORequest) {
        //antes de atualizar busca se existe o registro a ser atualizar
        Materia materia = this.materiaRepository.obterMateriaPorId(materiaId);
        if (materia == null){
            throw new IllegalArgumentException("Materia não existe");
        }
        modelMapper.map(materiaDTORequest,materia);
        Materia materiaAtualizada = this.materiaRepository.save(materia);
        return modelMapper.map(materiaAtualizada, MateriaDTOResponse.class);


    }

    public void apagarMateria(Integer materiaId){
        materiaRepository.apagadoLogicoMateria(materiaId);
    }
}