package com.Aprova.demo.Repository;

import com.Aprova.demo.Entity.Flashcard;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Flashcard f SET f.status = -1 WHERE f.id = :id")
    void apagadoLogicoFlashcard(@Param("id") Integer flashcardId);

    @Query("SELECT f FROM Flashcard f WHERE f.status >= 0")
    List<Flashcard> listarFlashcards();

    @Query("SELECT f FROM Flashcard f WHERE f.materia.id = :materiaId AND f.status >= 0")
    List<Flashcard> listarFlashcardsPorMateria(@Param("materiaId") Integer materiaId);

    @Query("SELECT f FROM Flashcard f WHERE f.id = :id AND f.status >= 0")
    Flashcard obterFlashcardPorId(@Param("id") Integer flashcardId);
}