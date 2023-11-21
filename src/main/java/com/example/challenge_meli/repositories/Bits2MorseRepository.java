package com.example.challenge_meli.repositories;

import com.example.challenge_meli.model.InvalidTranslationException;
import com.example.challenge_meli.model.translators.Bits2Morse;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class Bits2MorseRepository {
    private static final Map<String, Bits2Morse> repo = new HashMap<>();

    public static Bits2Morse getTranslator(String username) throws InvalidTranslationException {
        if(repo.get(username) == null){
            throw new InvalidTranslationException("Recordá calibrar previamente para tener una traducción certera :)");
        }
        return repo.get(username);
    }

    public static void setTranslator(String username, Bits2Morse bits2Morse) {
        repo.put(username, bits2Morse);
    }

}
