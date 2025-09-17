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

    public List<Metas> listarMetas(){
        return this.metasRepository.listarMetas();
    }

    public Metas listarMetasId(Integer metasId) {
        return this.metasRepository.obterMetasPorId(metasId);
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
        //antes de atualizar busca se existe o registro a ser atualizar
        Metas metas = this.listarMetasId(metasId);

        //se encontra o registro a ser atualizado
        if (metas != null){
            //copia os dados a serem atualizados do DTO de entrada para um objeto do tipo participante
            //que é compatível com o repository para atualizar
            modelMapper.map(metasDTORequest, metas);

            //com o objeto no formato correto tipo "participante" o comando "save" salva
            // no banco de dados o objeto atualizado
            Metas tempResponse = metasRepository.save(metas);

            return modelMapper.map(tempResponse, MetasDTOResponse.class);
        }else {
            return null;
        }

    }

    public MetasDTOUpdateResponse atualizarStatusMetas(Integer metasId, MetasDTOUpdateRequest metasDTOUpdateRequest) {
        //antes de atualizar busca se existe o registro a ser atualizar
        Metas metas = this.listarMetasId(metasId);

        //se encontra o registro a ser atualizado
        if (metas != null) {
            //atualizamos unicamente o campo de status
            metas.setStatus(metasDTOUpdateRequest.getStatus());

            //com o objeto no formato correto tipo "participante" o comando "save" salva
            // no banco de dados o objeto atualizado
            Metas tempResponse = metasRepository.save(metas);
            return modelMapper.map(tempResponse, MetasDTOUpdateResponse.class);
        }
        else{
            return null;
        }
    }

    public void apagarMetas(Integer metasId){
        metasRepository.apagarMetas(metasId);
    }
}