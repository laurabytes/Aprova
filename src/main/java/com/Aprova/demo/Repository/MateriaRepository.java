package com.Aprova.demo.Repository;

import com.Aprova.demo.Entity.Materia;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Materia p SET p.status = -1 WHERE p.id = :id")
    void apagadoLogicoMateria(@Param("id") Integer materiaId);

    @Query("SELECT p FROM Materia p WHERE p.status >= 0")
    List<Materia> listarMaterias();

    @Query("SELECT p FROM Materia p WHERE p.id=:id AND p.status >= 0")
    Materia obterMateriaPorId(@Param("id") Integer materiaId);
}