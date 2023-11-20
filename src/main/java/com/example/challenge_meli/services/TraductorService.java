package com.example.challenge_meli.services;

import com.example.challenge_meli.model.InvalidInputException;
import com.example.challenge_meli.model.InvalidTranslationException;
import com.example.challenge_meli.model.translators.Bits2Morse;
import com.example.challenge_meli.model.translators.Human2Morse;
import com.example.challenge_meli.model.translators.Morse2Human;
import org.springframework.stereotype.Service;

@Service
public class TraductorService {

    public String decodeBits2Morse(String cadena) throws InvalidInputException, InvalidTranslationException {
        Bits2Morse decodeMorse = new Bits2Morse();
        decodeMorse.validateInput(cadena);
        return decodeMorse.translate(cadena);
    }

    public String calibrate(String bitSequence) throws InvalidInputException{
        Bits2Morse decodeMorse = new Bits2Morse();
        decodeMorse.validateInput(bitSequence);
        decodeMorse.calibrateTranslator(bitSequence);
        String message = "Calibración lista! Ya puedes enviar tu mensaje.";
        return message;

    }


    public String morse2Human(String cadena) throws InvalidInputException{
        Morse2Human morse2Human = new Morse2Human();
        morse2Human.validateInput(cadena);
        return morse2Human.translate(cadena);
    }

    public String human2Morse(String cadena) throws InvalidInputException{
        Human2Morse human2Morse = new Human2Morse();
        human2Morse.validateInput(cadena);
        return human2Morse.translate(cadena);
    }
}