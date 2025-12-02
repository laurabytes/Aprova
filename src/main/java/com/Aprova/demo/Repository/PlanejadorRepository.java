package com.Aprova.demo.Repository;

import com.Aprova.demo.Entity.Planejador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanejadorRepository extends JpaRepository<Planejador, Integer> {
    // Buscar todos os itens de um usuário específico
    List<Planejador> findByUsuarioId(Integer usuarioId);
}
