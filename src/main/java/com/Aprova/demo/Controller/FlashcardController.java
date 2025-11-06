package com.Aprova.demo.Controller;

import com.Aprova.demo.Service.FlashcardService;
import com.Aprova.demo.dto.request.FlashcardDTORequest;
import com.Aprova.demo.dto.response.FlashcardDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/flashcards")
public class FlashcardController {

    @Autowired
    private FlashcardService flashcardService;

    @GetMapping("/listar")
    public ResponseEntity<List<FlashcardDTOResponse>> listarFlashcards() {
        return ResponseEntity.ok(flashcardService.listarFlashcards());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlashcardDTOResponse> listarFlashcardId(@PathVariable Integer id) {
        return ResponseEntity.ok(flashcardService.listarFlashcardId(id));
    }

    @GetMapping("/listar/materia/{materiaId}")
    public ResponseEntity<List<FlashcardDTOResponse>> listarFlashcardsPorMateria(@PathVariable Integer materiaId) {
        return ResponseEntity.ok(flashcardService.listarFlashcardsPorMateria(materiaId));
    }

    @PostMapping("/criar")
    public ResponseEntity<FlashcardDTOResponse> criarFlashcard(@RequestBody FlashcardDTORequest flashcardDTORequest) {
        FlashcardDTOResponse response = flashcardService.criarFlashcard(flashcardDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<FlashcardDTOResponse> atualizarFlashcard(@PathVariable Integer id, @RequestBody FlashcardDTORequest flashcardDTORequest) {
        FlashcardDTOResponse response = flashcardService.atualizarFlashcard(id, flashcardDTORequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<Void> apagarFlashcard(@PathVariable Integer id) {
        flashcardService.apagarFlashcard(id);
        return ResponseEntity.noContent().build();
    }
}