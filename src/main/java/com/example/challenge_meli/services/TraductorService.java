package com.example.challenge_meli.services;

import com.example.challenge_meli.model.InvalidInputException;
import com.example.challenge_meli.model.InvalidTranslationException;
import com.example.challenge_meli.model.translators.Bits2Morse;
import com.example.challenge_meli.model.translators.Human2Morse;
import com.example.challenge_meli.model.translators.Morse2Human;
import org.springframework.stereotype.Service;

@Service
public class TraductorService {

    public String decodeBits2Morse(String bitSequence) throws InvalidInputException, InvalidTranslationException {
        Bits2Morse decodeMorse = new Bits2Morse();
        decodeMorse.validateInput(bitSequence);
        return decodeMorse.translate(bitSequence);
    }
    public String translate2Human(String morseText) throws InvalidInputException{
        Morse2Human morse2Human = new Morse2Human();
        morse2Human.validateInput(morseText);
        return morse2Human.translate(morseText);
    }

    public String human2Morse(String humanText) throws InvalidInputException{
        Human2Morse human2Morse = new Human2Morse();
        human2Morse.validateInput(humanText);
        return human2Morse.translate(humanText);
    }
}