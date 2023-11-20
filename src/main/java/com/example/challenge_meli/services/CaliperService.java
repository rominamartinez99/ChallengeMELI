package com.example.challenge_meli.services;

import com.example.challenge_meli.model.InvalidInputException;
import com.example.challenge_meli.model.translators.Bits2Morse;
import org.springframework.stereotype.Service;

@Service
public class CaliperService {
    public String calibrate(String bitSequence) throws InvalidInputException {
        Bits2Morse decodeMorse = new Bits2Morse();
        decodeMorse.validateInput(bitSequence);
        decodeMorse.calibrateTranslator(bitSequence);
        return "Calibración lista! Ya puedes enviar tu mensaje.";

    }
}
