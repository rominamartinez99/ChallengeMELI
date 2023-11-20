package com.example.challenge_meli.model.translators;

import com.example.challenge_meli.model.InvalidInputException;
import com.example.challenge_meli.model.MapTranslations;

public class Human2Morse implements ITraductor{

    public Human2Morse() {
    }

    @Override
    public void validateInput(String bitSequence) throws InvalidInputException {
        if (!bitSequence.matches("[a-zA-Z0-9 ]+")) {
            throw new InvalidInputException("No se pueden traducir caracteres especiales");
        }
    }

    @Override
    public String translate(String bitSequence) throws InvalidInputException {

        char[] arr = bitSequence.toUpperCase().toCharArray();
        StringBuilder translation = new StringBuilder();
        for(char ch : arr){
            if (!MapTranslations.getInstance().getMapHuman2Morse().containsKey(ch)) {
                throw new InvalidInputException("No se puede traducir el caracter: " + ch);
            }
            translation.append(MapTranslations.getInstance().getMapHuman2Morse().get(ch)).append(" ");
        }

        return translation.toString().trim();
    }
}
