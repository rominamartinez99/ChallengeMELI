package com.example.challenge_meli.model.translators;

import java.util.*;
import java.util.stream.Collectors;


public class DecodeMorse implements ITraductor{


    private static int shortZero;
    private static int mediumZero;
    private static int longZero;
    private static int oneThreshold;
    private static final String PATTERN_SEPARATOR_BY_ZEROS = "(?=(?!^)0)(?<!0)|(?!0)(?<=0)";

    public DecodeMorse() {
    }

    @Override
    public void validateInput(String bitSequence) {
        if (!bitSequence.matches("[01]+")) {
            throw new IllegalArgumentException("La secuencia debe contener solo caracteres '0' y '1'");
        }
    }

    public static void calibrateTranslator(String bitSequence) {
        List<String> listSeparatedByZeros = separateByZeros(bitSequence);
        List<String> onesList = filterBySubstring(listSeparatedByZeros, "1");
        List<String> zerosList = filterBySubstring(listSeparatedByZeros, "0");
        List<Integer> zeroCounts = countDigitsPerPosition(zerosList);
        List<Integer> oneCounts = countDigitsPerPosition(onesList);
        int maxOne = oneCounts.stream().mapToInt(Integer::intValue).max().orElse(0);
        int minOne = oneCounts.stream().mapToInt(Integer::intValue).min().orElse(0);
        oneThreshold = (maxOne + minOne) / 2;

        longZero = zeroCounts.stream().mapToInt(Integer::intValue).max().orElse(0);
        shortZero = zeroCounts.stream().mapToInt(Integer::intValue).min().orElse(0);
        mediumZero = (longZero + shortZero) / 2;
    }

    public static String dotOrDah(String substring) {
        return substring.length() < oneThreshold ? "." : "-";
    }

    public static String amountSpaces(String substring) {
        int value = substring.length();

        int longDistance = Math.abs(longZero - value);
        int mediumDistance = Math.abs(mediumZero - value);
        int shortDistance = Math.abs(shortZero - value);

        if (longDistance <= mediumDistance && longDistance <= shortDistance) {
            return "   ";
        } else if (mediumDistance <= longDistance && mediumDistance <= shortDistance) {
            return " ";
        } else {
            return "";
        }
    }

    private static List<String> separateByZeros(String bitSequence) {
        List<String> messageSeparatedByZerosAndOnes = Arrays.stream(bitSequence.split(PATTERN_SEPARATOR_BY_ZEROS))
                .filter(dotOrDah -> !dotOrDah.isEmpty())
                .collect(Collectors.toList());

        if (!messageSeparatedByZerosAndOnes.isEmpty() && messageSeparatedByZerosAndOnes.get(0).contains("0")) {
            // REMOVER LA PAUSA INICIAL Y FINAL
            messageSeparatedByZerosAndOnes.remove(0);
            messageSeparatedByZerosAndOnes.remove(messageSeparatedByZerosAndOnes.size() - 1);
        }

        return messageSeparatedByZerosAndOnes;
    }

    private static List<String> filterBySubstring(List<String> list, String substring) {
        return list.stream()
                .filter(element -> element.contains(substring))
                .toList();
    }

    private static List<Integer> countDigitsPerPosition(List<String> digitList) {
        int maxLength = digitList.size();
        int[] countArray = new int[maxLength];

        for (int i = 0; i < digitList.size(); i++) {
            countArray[i] = digitList.get(i).length();
        }
        return List.of(Arrays.stream(countArray).boxed().toArray(Integer[]::new));
    }

    @Override
    public String translate(String bitSequence) {
        List<String> listSeparatedByZeros = separateByZeros(bitSequence);

        StringBuilder morseResult = new StringBuilder();

        for (String substring : listSeparatedByZeros) {
            if (substring.contains("0")) {
                morseResult.append(amountSpaces(substring));
            } else if (substring.contains("1")) {
                morseResult.append(dotOrDah(substring));
            }
        }
        return morseResult.toString();
    }
}

