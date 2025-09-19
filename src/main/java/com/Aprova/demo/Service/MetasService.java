package com.Aprova.demo.Service;

import com.Aprova.demo.Entity.Metas;
import com.Aprova.demo.Entity.Usuario;
import com.Aprova.demo.Repository.MetasRepository;
import com.Aprova.demo.Repository.UsuarioRepository;
import com.Aprova.demo.dto.request.MetasDTORequest;
import com.Aprova.demo.dto.request.MetasDTOUpdateRequest;
import com.Aprova.demo.dto.response.MetasDTOResponse;
import com.Aprova.demo.dto.response.MetasDTOUpdateResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MetasService {

    private final MetasRepository metasRepository;
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private ModelMapper modelMapper;

    public MetasService(MetasRepository metasRepository, UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.metasRepository = metasRepository;
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }

    public List<MetasDTOResponse> listarMetas(){

        List<Metas> listarMetas = metasRepository.listarMetas();
        List<MetasDTOResponse> responses = listarMetas.stream()
                .map(metas -> modelMapper.map(metas,MetasDTOResponse.class))
                .collect(Collectors.toList());
        return responses;
    }

    public Metas listarMetasId(Integer metasId) {
        Metas meta = this.metasRepository.obterMetasPorId(metasId);
        if (meta == null || meta.getStatus() < 0) {
            throw new IllegalArgumentException("Meta com ID " + metasId + " não encontrada.");
        }
        return meta;
    }

    public MetasDTOResponse criarMetas(MetasDTORequest metasDTOrequest) {

        Usuario usuario = usuarioRepository.obterUsuarioPorId(metasDTOrequest.getUsuarioId());

        if (usuario == null){
            throw new IllegalArgumentException("Usuario não existe");
        }

        Metas meta = new Metas();
        meta.setNome(metasDTOrequest.getNome());
        meta.setData(metasDTOrequest.getData());
        meta.setStatus(metasDTOrequest.getStatus());
        meta.setUsuario(usuario);

        Metas metasSave = this.metasRepository.save(meta);
        MetasDTOResponse metasDTOResponse = modelMapper.map(metasSave, MetasDTOResponse.class);
        return metasDTOResponse;
    }

    public MetasDTOResponse atualizarMetas(Integer metasId, MetasDTORequest metasDTORequest) {
        Metas metas = this.listarMetasId(metasId);


        metas.setNome(metasDTORequest.getNome());
        metas.setData(metasDTORequest.getData());
        metas.setStatus(metasDTORequest.getStatus());

        Metas tempResponse = metasRepository.save(metas);

        return modelMapper.map(tempResponse, MetasDTOResponse.class);
    }

    public MetasDTOUpdateResponse atualizarStatusMetas(Integer metasId, MetasDTOUpdateRequest metasDTOUpdateRequest) {
        Metas metas = this.listarMetasId(metasId);
        metas.setStatus(metasDTOUpdateRequest.getStatus());
        Metas tempResponse = metasRepository.save(metas);
        return modelMapper.map(tempResponse, MetasDTOUpdateResponse.class);
    }

    public void apagarMetas(Integer metasId){
        Metas meta = this.listarMetasId(metasId);
        metasRepository.apagarMetas(metasId);
    }
}