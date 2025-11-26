package com.Aprova.demo.Service;

import com.Aprova.demo.Entity.Flashcard;
import com.Aprova.demo.Entity.Materia;
import com.Aprova.demo.Repository.FlashcardRepository;
import com.Aprova.demo.Repository.MateriaRepository;
import com.Aprova.demo.dto.request.FlashcardDTORequest;
import com.Aprova.demo.dto.response.FlashcardDTOResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlashcardService {

    private final FlashcardRepository flashcardRepository;
    private final MateriaRepository materiaRepository;
    @Autowired
    private ModelMapper modelMapper;

    public FlashcardService(FlashcardRepository flashcardRepository, MateriaRepository materiaRepository, ModelMapper modelMapper) {
        this.flashcardRepository = flashcardRepository;
        this.materiaRepository = materiaRepository;
        this.modelMapper = modelMapper;
    }

    public List<FlashcardDTOResponse> listarFlashcards() {
        List<Flashcard> flashcards = flashcardRepository.listarFlashcards();
        return flashcards.stream()
                .map(flashcard -> modelMapper.map(flashcard, FlashcardDTOResponse.class))
                .collect(Collectors.toList());
    }

    public List<FlashcardDTOResponse> listarFlashcardsPorMateria(Integer materiaId) {
        List<Flashcard> flashcards = flashcardRepository.listarFlashcardsPorMateria(materiaId);
        return flashcards.stream()
                .map(flashcard -> modelMapper.map(flashcard, FlashcardDTOResponse.class))
                .collect(Collectors.toList());
    }

    public FlashcardDTOResponse listarFlashcardId(Integer flashcardId) {
        Flashcard flashcard = flashcardRepository.obterFlashcardPorId(flashcardId);
        if (flashcard == null) {
            throw new IllegalArgumentException("Flashcard com ID " + flashcardId + " não encontrado.");
        }
        return modelMapper.map(flashcard, FlashcardDTOResponse.class);
    }

    public FlashcardDTOResponse criarFlashcard(FlashcardDTORequest flashcardDTORequest) {
        Materia materia = materiaRepository.obterMateriaPorId(flashcardDTORequest.getMateriaId());
        if (materia == null) {
            throw new IllegalArgumentException("Matéria com ID " + flashcardDTORequest.getMateriaId() + " não encontrada.");
        }

        Flashcard novoFlashcard = new Flashcard();
        novoFlashcard.setPergunta(flashcardDTORequest.getPergunta());
        novoFlashcard.setResposta(flashcardDTORequest.getResposta());
        novoFlashcard.setPonto(flashcardDTORequest.getPonto());
        novoFlashcard.setStatus(1);
        novoFlashcard.setMateria(materia);

        Flashcard flashcardSalvo = this.flashcardRepository.save(novoFlashcard);
        return modelMapper.map(flashcardSalvo, FlashcardDTOResponse.class);
    }

    public FlashcardDTOResponse atualizarFlashcard(Integer flashcardId, FlashcardDTORequest flashcardDTORequest) {
        Flashcard flashcard = this.flashcardRepository.obterFlashcardPorId(flashcardId);
        if (flashcard == null) {
            throw new IllegalArgumentException("Flashcard com ID " + flashcardId + " não encontrado.");
        }

        Materia materia = materiaRepository.obterMateriaPorId(flashcardDTORequest.getMateriaId());
        if (materia == null) {
            throw new IllegalArgumentException("Matéria com ID " + flashcardDTORequest.getMateriaId() + " não encontrada.");
        }

        flashcard.setPergunta(flashcardDTORequest.getPergunta());
        flashcard.setResposta(flashcardDTORequest.getResposta());
        flashcard.setPonto(flashcardDTORequest.getPonto());
        flashcard.setStatus(1);
        flashcard.setMateria(materia);

        Flashcard flashcardAtualizado = this.flashcardRepository.save(flashcard);
        return modelMapper.map(flashcardAtualizado, FlashcardDTOResponse.class);
    }

    public void apagarFlashcard(Integer flashcardId) {
        Flashcard flashcard = this.flashcardRepository.obterFlashcardPorId(flashcardId);
        if (flashcard == null) {
            throw new IllegalArgumentException("Flashcard com ID " + flashcardId + " não encontrado.");
        }
        flashcardRepository.apagadoLogicoFlashcard(flashcardId);
    }
}