package com.example.ChallengeMELI.Controllers;
import com.example.ChallengeMELI.Model.TranslationRequest;
import com.example.ChallengeMELI.Services.TraductorService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class TraductorController {

    private final TraductorService servicio;

    // Inyección de dependencias del servicio
    public TraductorController(TraductorService servicio) {
        this.servicio = servicio;
    }

    @PostMapping("/decodeMorse")
    public String decodeMorse(@RequestBody TranslationRequest request) {
        return servicio.decodeMorse(request);
    }
    @PostMapping("/morse2human")
    public String obtenerMorse2Human(@RequestBody TranslationRequest request) {
        return servicio.Morse2Human(request);
    }

    @PostMapping ("/human2morse")
    public String obtenerHuman2Morse(@RequestBody TranslationRequest request) {
        return servicio.Human2Morse(request);
    }
}
