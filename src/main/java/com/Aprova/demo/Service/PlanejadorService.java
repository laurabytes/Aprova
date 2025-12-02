package com.Aprova.demo.Service;

import com.Aprova.demo.Entity.Planejador;
import com.Aprova.demo.Entity.Usuario;
import com.Aprova.demo.Repository.PlanejadorRepository;
import com.Aprova.demo.Repository.UsuarioRepository;
import com.Aprova.demo.dto.request.PlanejadorDTORequest;
import com.Aprova.demo.dto.response.PlanejadorDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanejadorService {

    @Autowired
    private PlanejadorRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<PlanejadorDTOResponse> listarPorUsuario(Integer usuarioId) {
        List<Planejador> lista = repository.findByUsuarioId(usuarioId);
        return lista.stream()
                .map(p -> new PlanejadorDTOResponse(
                        p.getId(), p.getDia(), p.getHora(), p.getMin(), p.getDuracao(), p.getMateria()))
                .collect(Collectors.toList());
    }

    public PlanejadorDTOResponse criar(PlanejadorDTORequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        Planejador novo = new Planejador();
        novo.setDia(request.getDia());
        novo.setHora(request.getHora());
        novo.setMin(request.getMin());
        novo.setDuracao(request.getDuracao());
        novo.setMateria(request.getMateria());
        novo.setUsuario(usuario);

        Planejador salvo = repository.save(novo);

        return new PlanejadorDTOResponse(
                salvo.getId(), salvo.getDia(), salvo.getHora(), salvo.getMin(), salvo.getDuracao(), salvo.getMateria());
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    // Método para atualizar (caso queira editar no futuro)
    public PlanejadorDTOResponse atualizar(Integer id, PlanejadorDTORequest request) {
        Planejador existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        existente.setDia(request.getDia());
        existente.setHora(request.getHora());
        existente.setMin(request.getMin());
        existente.setDuracao(request.getDuracao());
        existente.setMateria(request.getMateria());
        // Não costuma-se alterar o usuário numa edição simples

        Planejador salvo = repository.save(existente);
        return new PlanejadorDTOResponse(
                salvo.getId(), salvo.getDia(), salvo.getHora(), salvo.getMin(), salvo.getDuracao(), salvo.getMateria());
    }
}