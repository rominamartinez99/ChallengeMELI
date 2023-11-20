package com.example.challenge_meli.controllers;
import com.example.challenge_meli.model.InvalidInputException;
import com.example.challenge_meli.model.InvalidTranslationException;
import com.example.challenge_meli.model.TranslationRequest;
import com.example.challenge_meli.model.TranslationResponse;
import com.example.challenge_meli.services.TraductorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/translate")
@SecurityRequirement(name = "in-memory")
public class TraductorController {

    private final TraductorService traductorService;

    // Inyección de dependencias del servicio
    public TraductorController(TraductorService servicio) {
        this.traductorService = servicio;
    }

    @PostMapping("/calibrate")
    public ResponseEntity<String> calibrate(@RequestBody TranslationRequest request) throws InvalidInputException {
        String bitSequence = request.getText();
        return ResponseEntity.ok(traductorService.calibrate(bitSequence));
    }

    @PostMapping("/bits2morse")
    public ResponseEntity<TranslationResponse> decodeMorse(@RequestBody TranslationRequest request) throws InvalidInputException, InvalidTranslationException {
        String bitSequence = request.getText();
        return ResponseEntity.ok(new TranslationResponse(traductorService.decodeBits2Morse(bitSequence)));
    }
    @PostMapping("/2human")
    public ResponseEntity<TranslationResponse> morse2Human(@RequestBody TranslationRequest request) throws InvalidInputException{
        String cadena = request.getText();
        return ResponseEntity.ok(new TranslationResponse(traductorService.morse2Human(cadena)));
    }

    @PostMapping ("/2morse")
    public ResponseEntity<TranslationResponse> human2Morse(@RequestBody TranslationRequest request) throws InvalidInputException{
        String cadena = request.getText();
        return ResponseEntity.ok(new TranslationResponse(traductorService.human2Morse(cadena)));
    }
}
