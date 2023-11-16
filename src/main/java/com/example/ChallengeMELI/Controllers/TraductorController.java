package com.example.ChallengeMELI.Controllers;
import com.example.ChallengeMELI.Model.TranslationRequest;
import com.example.ChallengeMELI.Model.TranslationResponse;
import com.example.ChallengeMELI.Services.TraductorService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
        return new TranslationResponse(servicio.Morse2Human(cadena));
    }

    @PostMapping ("/2morse")
    public TranslationResponse obtenerHuman2Morse(@RequestBody TranslationRequest request) {
        String cadena = request.getText();
        return new TranslationResponse(servicio.Human2Morse(cadena));
    }
}
