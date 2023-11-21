package com.example.challenge_meli.services;

import com.example.challenge_meli.model.InvalidInputException;
import com.example.challenge_meli.model.translators.Bits2Morse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.challenge_meli.repositories.Bits2MorseRepository;

@Service
public class CaliperService {

    @Autowired
    Bits2MorseRepository repo;

    public String calibrate(String bitSequence, String username) throws InvalidInputException {
        Bits2Morse bits2MorseTranslator = new Bits2Morse();
        bits2MorseTranslator.validateInput(bitSequence);
        bits2MorseTranslator.calibrateTranslator(bitSequence);
        repo.setTranslator(username,bits2MorseTranslator);
        return "Calibración lista! Ya puedes enviar tu mensaje.";
    }
}
