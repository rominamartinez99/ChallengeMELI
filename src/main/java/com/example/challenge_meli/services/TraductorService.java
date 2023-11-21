package com.example.challenge_meli.services;

import com.example.challenge_meli.model.InvalidInputException;
import com.example.challenge_meli.model.InvalidTranslationException;
import com.example.challenge_meli.model.translators.Bits2Morse;
import com.example.challenge_meli.model.translators.Human2Morse;
import com.example.challenge_meli.model.translators.Morse2Human;
import org.springframework.stereotype.Service;
import com.example.challenge_meli.repositories.Bits2MorseRepository;

@Service
public class TraductorService {

    Bits2MorseRepository bits2MorseRepository;

    public String bits2Morse(String bitSequence, String username) throws InvalidInputException, InvalidTranslationException {
        Bits2Morse decodeMorse = Bits2MorseRepository.getTranslator(username);
        decodeMorse.validateInput(bitSequence);
        return decodeMorse.decodeBits2Morse(bitSequence);
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