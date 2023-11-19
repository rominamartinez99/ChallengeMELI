package com.example.challenge_meli.controllers;
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

    private final TraductorService servicio;

    // Inyección de dependencias del servicio
    public TraductorController(TraductorService servicio) {
        this.servicio = servicio;
    }

    @PostMapping("/bits2morse")
    public ResponseEntity<TranslationResponse> decodeMorse(@RequestBody TranslationRequest request) {
        String cadena = request.getText();
        return ResponseEntity.ok(new TranslationResponse(servicio.decodeMorse(cadena)));
    }
    @PostMapping("/2human")
    public ResponseEntity<TranslationResponse> obtenerMorse2Human(@RequestBody TranslationRequest request) {
        String cadena = request.getText();
        return ResponseEntity.ok(new TranslationResponse(servicio.morse2Human(cadena)));
    }

    @PostMapping ("/2morse")
    public ResponseEntity<TranslationResponse> obtenerHuman2Morse(@RequestBody TranslationRequest request) {
        String cadena = request.getText();
        return ResponseEntity.ok(new TranslationResponse(servicio.human2Morse(cadena)));
    }
}
