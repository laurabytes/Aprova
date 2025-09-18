package com.Aprova.demo.Service;

import com.Aprova.demo.Entity.Materia;
import com.Aprova.demo.Entity.Usuario;
import com.Aprova.demo.Repository.MateriaRepository;
import com.Aprova.demo.Repository.UsuarioRepository;
import com.Aprova.demo.dto.request.MateriaDTORequest;
import com.Aprova.demo.dto.response.MateriaDTOResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        List<Materia> materias = materiaRepository.listarMaterias();
        return materias.stream()
                .map(materia -> modelMapper.map(materia, MateriaDTOResponse.class))
                .collect(Collectors.toList());
    }


    public MateriaDTOResponse listarMateriaId(Integer materiaId) {
        Materia materia = materiaRepository.obterMateriaPorId(materiaId);
        if (materia == null) {
            throw new IllegalArgumentException("Matéria com ID " + materiaId + " não encontrada.");
        }
        return modelMapper.map(materia, MateriaDTOResponse.class);
    }

    public MateriaDTOResponse criarMateria(MateriaDTORequest materiaDTOrequest) {
        Usuario usuario = usuarioRepository.obterUsuarioPorId(materiaDTOrequest.getUsuarioId());
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário com ID " + materiaDTOrequest.getUsuarioId() + " não encontrado para associar à matéria.");
        }


        Materia novaMateria = new Materia();
        novaMateria.setNome(materiaDTOrequest.getNome());
        novaMateria.setCor(materiaDTOrequest.getCor());
        novaMateria.setPrioridade(materiaDTOrequest.getPrioridade());
        novaMateria.setStatus(materiaDTOrequest.getStatus());
        novaMateria.setUsuario(usuario);

        Materia materiaSalva = this.materiaRepository.save(novaMateria);

        return modelMapper.map(materiaSalva, MateriaDTOResponse.class);
    }

    public MateriaDTOResponse atualizarMateria(Integer materiaId, MateriaDTORequest materiaDTORequest) {
        Materia materia = this.materiaRepository.obterMateriaPorId(materiaId);
        if (materia == null){
            throw new IllegalArgumentException("Matéria com ID " + materiaId + " não encontrada para atualização.");
        }

        materia.setNome(materiaDTORequest.getNome());
        materia.setCor(materiaDTORequest.getCor());
        materia.setPrioridade(materiaDTORequest.getPrioridade());
        materia.setStatus(materiaDTORequest.getStatus());

        Materia materiaAtualizada = this.materiaRepository.save(materia);
        return modelMapper.map(materiaAtualizada, MateriaDTOResponse.class);
    }

    public void apagarMateria(Integer materiaId){
        materiaRepository.apagadoLogicoMateria(materiaId);
    }
}