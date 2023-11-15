package com.example.ChallengeMELI.Controllers;
import com.example.ChallengeMELI.Services.TraductorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TraductorController {

    private final TraductorService servicio;

    // Inyección de dependencias del servicio
    public TraductorController(TraductorService servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/saludo")
    public String obtenerSaludo() {
        return servicio.generarSaludo();
    }
}
