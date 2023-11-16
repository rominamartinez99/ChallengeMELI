package com.example.ChallengeMELI.Model.Translators;

import com.example.ChallengeMELI.Model.MapTranslations;

public class Morse2Human implements ITraductor{
    public Morse2Human() {
    }

    @Override
    public void validateInput(String bitSequence) {
        if (!bitSequence.matches("[\\.\\- ]+")) {
            throw new IllegalArgumentException("Solo se pueden ingresar puntos, guiones y espacios");
        }
    }

    @Override
    public String translate(String bitSequence) {
        String[] morseWords = bitSequence.split("  ");  // Dos espacios como separador de palabras
        StringBuilder translation = new StringBuilder();

        for (String morseWord : morseWords) {
            String[] morseChars = morseWord.split(" ");
            for (String morseChar : morseChars) {
                Character humanCharacter = MapTranslations.getInstance().getMapMorse2Human().get(morseChar);
                if (humanCharacter != null) {
                    translation.append(humanCharacter);
                } else {
                    // Si no hay una correspondencia válida
                    translation.append("?");
                }
            }
            translation.append(" ");  // Agregar espacio entre palabras en la traducción
        }

        return translation.toString().trim();  // Eliminar espacio adicional al final, si lo hay
    }
}
