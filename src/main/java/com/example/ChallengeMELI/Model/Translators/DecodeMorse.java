package com.example.ChallengeMELI.Model.Translators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecodeMorse implements ITraductor{

    public DecodeMorse() {
    }

    @Override
    public void validateInput(String bitSequence) {
        if (!bitSequence.matches("[01]+")) {
            throw new IllegalArgumentException("La secuencia debe contener solo caracteres '0' y '1'");
        }
    }

    @Override
    public String translate(String bitSequence) {
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
    }
}
