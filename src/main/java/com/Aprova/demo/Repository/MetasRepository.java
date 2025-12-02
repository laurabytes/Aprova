package com.Aprova.demo.Repository;

import com.Aprova.demo.Entity.Metas;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetasRepository extends JpaRepository<Metas, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Metas m SET m.status = -1 WHERE m.metasId = :id")
    void apagarMetas(@Param("id") Integer metasId);

    // CORREÇÃO: Adicionado filtro por usuarioId
    @Query("SELECT m FROM Metas m WHERE m.usuario.id = :usuarioId AND m.status >= 0")
    List<Metas> listarMetas(@Param("usuarioId") Integer usuarioId);

    @Query("SELECT m FROM Metas m WHERE m.metasId = :id")
    Metas obterMetasPorId(@Param("id") Integer metasId);
}