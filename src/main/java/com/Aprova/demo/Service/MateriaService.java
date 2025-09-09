package com.Aprova.demo.Service;

import com.Aprova.demo.Entity.Materia;
import com.Aprova.demo.Repository.MateriaRepository;
import com.Aprova.demo.dto.request.MateriaDTORequest;
import com.Aprova.demo.dto.response.MateriaDTOResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaService {

    private final MateriaRepository materiaRepository;
    @Autowired
    private ModelMapper modelMapper;

    public MateriaService(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    public List<Materia> listarMaterias(){
        return this.materiaRepository.listarMaterias();
    }

    public Materia listarMateriaId(Integer materiaId) {
        return this.materiaRepository.obterMateriaPorId(materiaId);
    }

    public MateriaDTOResponse criarMateria(MateriaDTORequest materiaDTOrequest) {

        Materia materia = modelMapper.map(materiaDTOrequest, Materia.class);
        Materia materiaSave = this.materiaRepository.save(materia);
        MateriaDTOResponse materiaDTOResponse = modelMapper.map(materiaSave, MateriaDTOResponse.class);
        return materiaDTOResponse;
    }

    public MateriaDTOResponse atualizarMateria(Integer materiaId, MateriaDTORequest materiaDTORequest) {
        //antes de atualizar busca se existe o registro a ser atualizar
        Materia materia = this.listarMateriaId(materiaId);

        //se encontra o registro a ser atualizado
        if (materia != null){
            //copia os dados a serem atualizados do DTO de entrada para um objeto do tipo participante
            //que é compatível com o repository para atualizar
            modelMapper.map(materiaDTORequest,materia);

            //com o objeto no formato correto tipo "participante" o comando "save" salva
            // no banco de dados o objeto atualizado
            Materia tempResponse = materiaRepository.save(materia);

            return modelMapper.map(tempResponse, MateriaDTOResponse.class);
        }else {
            return null;
        }

    }

//    public MateriaDTOUpdateResponse atualizarStatusMateria(Integer materiaId, MateriaDTOUpdateRequest materiaDTOUpdateRequest) {
//        //antes de atualizar busca se existe o registro a ser atualizar
//        Materia materia = this.listarMateriaId(materiaId);
//
//        //se encontra o registro a ser atualizado
//        if (materia != null) {
//            //atualizamos unicamente o campo de status
//            materia.setStatus(materiaDTOUpdateRequest.getStatus());
//
//            //com o objeto no formato correto tipo "participante" o comando "save" salva
//            // no banco de dados o objeto atualizado
//            Pedido tempResponse = pedidoRepository.save(pedido);
//            return modelMapper.map(tempResponse, PedidoDTOUpdateResponse.class);
//        }
//        else{
//            return null;
//        }
//    }

    public void apagarMateria(Integer materiaId){
        materiaRepository.apagadoLogicoMateria(materiaId);
    }
}