package com.example.ChallengeMELI.Services;

import com.example.ChallengeMELI.Model.*;
import com.example.ChallengeMELI.Model.Translators.DecodeMorse;
import com.example.ChallengeMELI.Model.Translators.Human2Morse;
import com.example.ChallengeMELI.Model.Translators.Morse2Human;
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

    public String Morse2Human(TranslationRequest request) {
        String cadena = request.getText();
        Morse2Human morse2Human = new Morse2Human();

        try {
            morse2Human.validateInput(cadena);
            return morse2Human.translate(cadena);

        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String Human2Morse(TranslationRequest request) {
        String cadena = request.getText();
        Human2Morse human2Morse = new Human2Morse();

        try {
            human2Morse.validateInput(cadena);
            return human2Morse.translate(cadena);

        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }
}