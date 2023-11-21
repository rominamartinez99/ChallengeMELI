package com.example.challenge_meli.controllers;

import com.example.challenge_meli.model.InvalidInputException;
import com.example.challenge_meli.model.TranslationRequest;
import com.example.challenge_meli.services.CaliperService;
import com.example.challenge_meli.services.TraductorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calibrate")
@SecurityRequirement(name = "in-memory")
public class CaliperController {
    private final CaliperService caliperService;

    public CaliperController(CaliperService servicio) {
        this.caliperService = servicio;
    }


    @PostMapping("/paris")
    public ResponseEntity<String> calibrate(@RequestBody TranslationRequest request) throws InvalidInputException {
        String bitSequence = request.getText();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(caliperService.calibrate(bitSequence, authentication.getName()));
    }
}
