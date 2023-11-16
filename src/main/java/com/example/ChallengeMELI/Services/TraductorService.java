package com.example.ChallengeMELI.Services;

import com.example.ChallengeMELI.Model.MapTranslations;
import com.example.ChallengeMELI.Model.TranslationRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TraductorService {

    public static String decodeMorse(TranslationRequest request) {
        try {
            String bitSequence = request.getSentence(); //TODO: MEJORAR ESTO CON ENCRIPTACION
            validateInput(bitSequence);

            List<Integer> ones = new ArrayList<>();
            List<Integer> zeros = new ArrayList<>();

            Pattern pattern = Pattern.compile("(1+|0+)");
            Matcher matcher = pattern.matcher(bitSequence);

            while (matcher.find()) {
                String match = matcher.group();
                char bit = match.charAt(0);
                int count = match.length();

                if (bit == '1') {
                    ones.add(count);
                } else {
                    zeros.add(count);
                }
            }

            // Eliminar la primera y última secuencia de ceros
            if (!zeros.isEmpty()) {
                zeros.remove(0);
                zeros.remove(zeros.size() - 1);
            }

            int maxOnesLength = Collections.max(ones);
            int minOnesLength = Collections.min(ones);

            double averageOnes = ones.stream().mapToInt(Integer::intValue).average().orElse(0);

            StringBuilder morseResult = new StringBuilder();

            for (int oneCount : ones) {
                if (oneCount < averageOnes) {
                    morseResult.append(".");
                } else {
                    morseResult.append("-");
                }
            }

            for (int zeroCount : zeros) {
                if (zeroCount < averageOnes) {
                    // Menor al promedio: espacio simple
                    morseResult.append(" ");
                } else {
                    // Mayor al promedio: dos espacios
                    morseResult.append("  ");
                }
            }

            return morseResult.toString();
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }

    private static void validateInput(String bitSequence) {
        if (!bitSequence.matches("[01]+")) {
            throw new IllegalArgumentException("La secuencia debe contener solo caracteres '0' y '1'");
        }
    }
    public String Morse2Human(TranslationRequest request) {
        try{
            MapTranslations mapM2H = new MapTranslations();
            String[] morseWords = request.getSentence().split("  ");  // Dos espacios como separador de palabras
            StringBuilder translation = new StringBuilder();

            for (String morseWord : morseWords) {
                String[] morseChars = morseWord.split(" ");
                for (String morseChar : morseChars) {
                    Character humanCharacter = mapM2H.getMapMorse2Human().get(morseChar);
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
        }catch (IllegalArgumentException e){
            return "Error: " + e.getMessage();
        }

    }

    public String Human2Morse(TranslationRequest request)
    {
        try{
            MapTranslations mapH2M = new MapTranslations();
            String humanSentence = request.getSentence();

            char[] arr = humanSentence.toUpperCase().toCharArray();
            StringBuilder translation = new StringBuilder();
            for(char ch : arr){
                translation.append(mapH2M.getMapHuman2Morse().get(ch));
            }

            return translation.toString();
        }catch (IllegalArgumentException e){
            return "Error: " + e.getMessage();
        }
    }
}