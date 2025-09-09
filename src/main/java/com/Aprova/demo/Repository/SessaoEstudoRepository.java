package com.Aprova.demo.Repository;

import com.Aprova.demo.Entity.SessaoEstudo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessaoEstudoRepository extends JpaRepository<SessaoEstudo, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE SessaoEstudo s SET s.status = -1 WHERE s.sessaoEstudoId = :id")
    void apagarSessaoEstudo(@Param("id") Integer sessaoEstudoId);

    @Query("SELECT s FROM SessaoEstudo s WHERE s.status >= 0")
    List<SessaoEstudo> listarSessoesEstudo();

    @Query("SELECT s FROM SessaoEstudo s WHERE s.sessaoEstudoId = :id")
    SessaoEstudo obterSessaoEstudoPorId(@Param("id") Integer sessaoEstudoId);
}