package com.example.challenge_meli.controllers;
import com.example.challenge_meli.model.InvalidInputException;
import com.example.challenge_meli.model.InvalidTranslationException;
import com.example.challenge_meli.model.TranslationRequest;
import com.example.challenge_meli.model.TranslationResponse;
import com.example.challenge_meli.services.TraductorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/translate")
@SecurityRequirement(name = "in-memory")
public class TraductorController {

    private final TraductorService traductorService;

    public TraductorController(TraductorService servicio) {
        this.traductorService = servicio;
    }

    @PostMapping("/bits2morse")
    public ResponseEntity<TranslationResponse> decodeMorse(@RequestBody TranslationRequest request) throws InvalidInputException, InvalidTranslationException {
        String bitSequence = request.getText();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(new TranslationResponse(traductorService.bits2Morse(bitSequence, authentication.getName())));
    }
    @PostMapping("/2human")
    public ResponseEntity<TranslationResponse> morse2Human(@RequestBody TranslationRequest request) throws InvalidInputException{
        String text = request.getText();
        return ResponseEntity.ok(new TranslationResponse(traductorService.translate2Human(text)));
    }

    @PostMapping ("/2morse")
    public ResponseEntity<TranslationResponse> human2Morse(@RequestBody TranslationRequest request) throws InvalidInputException{
        String text = request.getText();
        return ResponseEntity.ok(new TranslationResponse(traductorService.human2Morse(text)));
    }
}
