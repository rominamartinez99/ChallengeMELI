package com.example.challenge_meli.model.translators;

import com.example.challenge_meli.model.InvalidInputException;
import com.example.challenge_meli.model.MapTranslations;

public class Morse2Human implements ITraductor{
    public Morse2Human() {
    }

    @Override
    public void validateInput(String bitSequence) throws InvalidInputException {
        if (!bitSequence.matches("[\\.\\- ]+")) {
            throw new InvalidInputException("Solo se pueden ingresar puntos, guiones y espacios");
        }
    }

    @Override
    public String translate(String morseSequence) throws InvalidInputException {
        String[] morseWords = morseSequence.split(" {2}");  // Dos espacios como separador de palabras
        StringBuilder translation = new StringBuilder();

        for (String morseWord : morseWords) {
            String[] morseChars = morseWord.split(" ");
            for (String morseChar : morseChars) {
                Character humanCharacter = MapTranslations.getInstance().getMapMorse2Human().get(morseChar);
                if (humanCharacter != null) {
                    translation.append(humanCharacter);
                } else {
                    throw new InvalidInputException("No se puede traducir el caracter: " + morseChar);
                }
            }
            translation.append(" ");
        }
        return translation.toString().trim();
    }
}
