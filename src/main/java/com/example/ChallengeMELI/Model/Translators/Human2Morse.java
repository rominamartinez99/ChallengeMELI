package com.example.ChallengeMELI.Model.Translators;

import com.example.ChallengeMELI.Model.MapTranslations;

public class Human2Morse implements ITraductor{

    public Human2Morse() {
    }

    @Override
    public void validateInput(String bitSequence) {
        if (!bitSequence.matches("[a-zA-Z0-9 ]+")) {
            throw new IllegalArgumentException("No se pueden traducir caracteres especiales");
        }
    }

    @Override
    public String translate(String bitSequence) {
        MapTranslations mapH2M = new MapTranslations();

        char[] arr = bitSequence.toUpperCase().toCharArray();
        StringBuilder translation = new StringBuilder();
        for(char ch : arr){
            translation.append(mapH2M.getMapHuman2Morse().get(ch));
        }

        return translation.toString();
    }
}
