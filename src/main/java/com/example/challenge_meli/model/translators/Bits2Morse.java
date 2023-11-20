package com.example.challenge_meli.model.translators;

import com.example.challenge_meli.model.InvalidInputException;
import com.example.challenge_meli.model.InvalidTranslationException;

import java.util.*;
import java.util.stream.Collectors;


public class Bits2Morse implements ITraductor{
    private static int shortZero;
    private static int mediumZero;
    private static int longZero;
    private static double oneThreshold;
    private static final String PATTERN_SEPARATOR_BY_ZEROS = "(?=(?!^)0)(?<!0)|(?!0)(?<=0)";

    public Bits2Morse() {
    }

    @Override
    public void validateInput(String input) throws InvalidInputException {
        if (!input.matches("^[01]*1[01]*$")) {
            throw new InvalidInputException("La secuencia debe contener solo caracteres '0' y '1'. Además como mínimo debe haber un pulso");
        }
    }

    public void validateInput() throws InvalidTranslationException {
        if ((shortZero == 0 && mediumZero == 0 && longZero == 0)) {
            throw new InvalidTranslationException("Recordá calibrar previamente para tener una traducción certera :)");
        }
    }

    public static void calibrateTranslator(String bitSequence) {
        List<String> listSeparatedByZeros = separateByZeros(bitSequence);
        List<String> onesList = filterBySubstring(listSeparatedByZeros, "1");
        List<String> zerosList = filterBySubstring(listSeparatedByZeros, "0");
        Integer[] zeroCounts = countDigitsPerPosition(zerosList);
        Integer[] oneCounts = countDigitsPerPosition(onesList);
        oneThreshold = calculateMedian(oneCounts);

        longZero = Arrays.stream(zeroCounts).mapToInt(Integer::intValue).max().orElse(0);
        shortZero = Arrays.stream(zeroCounts).mapToInt(Integer::intValue).min().orElse(0);
        mediumZero = (longZero + shortZero) / 2;
    }

    public static double calculateMedian(Integer[] array) {
        Arrays.sort(array);
        int n = array.length;

        if (n % 2 == 0) {
            int medium1 = array[n / 2 - 1];
            int medium2 = array[n / 2];
            return (double) (medium1 + medium2) / 2;
        } else {
            return array[n / 2];
        }
    }

    public static String dotOrDah(String substring) {
        return substring.length() <= oneThreshold ? "." : "-";
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

    private static Integer[] countDigitsPerPosition(List<String> digitList) {
        int maxLength = digitList.size();
        int[] countArray = new int[maxLength];

        for (int i = 0; i < digitList.size(); i++) {
            countArray[i] = digitList.get(i).length();
        }
        return Arrays.stream(countArray).boxed().toArray(Integer[]::new);
    }

    @Override
    public String translate(String text) throws InvalidTranslationException {
        validateInput();
        List<String> listSeparatedByZeros = separateByZeros(text);

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

