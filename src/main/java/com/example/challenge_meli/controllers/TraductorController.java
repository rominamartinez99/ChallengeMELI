package com.example.challenge_meli.controllers;
import com.example.challenge_meli.model.TranslationRequest;
import com.example.challenge_meli.model.TranslationResponse;
import com.example.challenge_meli.services.TraductorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/translate")
public class TraductorController {

    private final TraductorService servicio;

    // Inyección de dependencias del servicio
    public TraductorController(TraductorService servicio) {
        this.servicio = servicio;
    }

    @PostMapping("/bits2morse")
    public TranslationResponse decodeMorse(@RequestBody TranslationRequest request) {
        String cadena = request.getText();
        return new TranslationResponse(servicio.decodeMorse(cadena));
    }
    @PostMapping("/2human")
    public TranslationResponse obtenerMorse2Human(@RequestBody TranslationRequest request) {
        String cadena = request.getText();
        return new TranslationResponse(servicio.morse2Human(cadena));
    }

    @PostMapping ("/2morse")
    public TranslationResponse obtenerHuman2Morse(@RequestBody TranslationRequest request) {
        String cadena = request.getText();
        return new TranslationResponse(servicio.human2Morse(cadena));
    }
}
