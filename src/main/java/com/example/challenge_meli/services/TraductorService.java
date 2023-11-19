package com.example.challenge_meli.services;

import com.example.challenge_meli.model.translators.DecodeMorse;
import com.example.challenge_meli.model.translators.Human2Morse;
import com.example.challenge_meli.model.translators.Morse2Human;
import org.springframework.stereotype.Service;

@Service
public class TraductorService {

    public String decodeMorse(String cadena) {
        DecodeMorse decodeMorse = new DecodeMorse();

        try {
            decodeMorse.validateInput(cadena);
            return decodeMorse.translate(cadena);

        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String calibrate(String bitSequence) {
        DecodeMorse decodeMorse = new DecodeMorse();

        try {
            decodeMorse.validateInput(bitSequence);
            decodeMorse.calibrateTranslator(bitSequence);
            String message = "Calibración lista! Ya puedes enviar tu mensaje.";
            return message;

        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }


    public String morse2Human(String cadena) {
        Morse2Human morse2Human = new Morse2Human();

        try {
            morse2Human.validateInput(cadena);
            return morse2Human.translate(cadena);

        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String human2Morse(String cadena) {
        Human2Morse human2Morse = new Human2Morse();

        try {
            human2Morse.validateInput(cadena);
            return human2Morse.translate(cadena);

        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }
}