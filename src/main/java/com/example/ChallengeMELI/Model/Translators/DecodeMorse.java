package com.example.ChallengeMELI.Model.Translators;

import java.util.*;
import java.util.stream.Collectors;


public class DecodeMorse {

    private static int shortZero;
    private static int mediumZero;
    private static int longZero;
    private static int umbralOne;
    private static final String PATTERN_SEPARATOR_BY_ZEROS = "(?=(?!^)0)(?<!0)|(?!0)(?<=0)";

    public DecodeMorse() {
    }

    public void validateInput(String bitSequence) {
        if (!bitSequence.matches("[01]+")) {
            throw new IllegalArgumentException("La secuencia debe contener solo caracteres '0' y '1'");
        }
    }

    public static void calibrarTraductor(List<String> listSeparatedByZeros) {
        List<String> onesList = filterBySubstring(listSeparatedByZeros, "1");
        List<String> zerosList = filterBySubstring(listSeparatedByZeros, "0");
        List<Integer> zeroCounts = countDigitsPerPosition(zerosList, '0');
        List<Integer> oneCounts = countDigitsPerPosition(onesList, '1');
        int maxOne = oneCounts.stream().mapToInt(Integer::intValue).max().orElse(0);
        int minOne = oneCounts.stream().mapToInt(Integer::intValue).min().orElse(0);
        umbralOne = (maxOne + minOne) / 2;

        longZero = zeroCounts.stream().mapToInt(Integer::intValue).max().orElse(0);
        shortZero = zeroCounts.stream().mapToInt(Integer::intValue).min().orElse(0);
        mediumZero = (longZero + shortZero) / 2;
    }

    public static String PuntoOraya(String subcadena) {
        return subcadena.length() < umbralOne ? "." : "-";
    }

    public static String AmountSpaces(String subcadena) {
        int valor = subcadena.length();

        int diferenciaMaximo = Math.abs(longZero - valor);
        int diferenciaMedio = Math.abs(mediumZero - valor);
        int diferenciaMinimo = Math.abs(shortZero - valor);

        if (diferenciaMaximo <= diferenciaMedio && diferenciaMaximo <= diferenciaMinimo) {
            return "   ";
        } else if (diferenciaMedio <= diferenciaMaximo && diferenciaMedio <= diferenciaMinimo) {
            return " ";
        } else {
            return "";
        }
    }

    private static List<String> separateByZeros(String bitSequence) {
        List<String> messageSeparatedByZerosAndOnes = Arrays.stream(bitSequence.split(PATTERN_SEPARATOR_BY_ZEROS))
                .filter(puntoOTrazo -> !puntoOTrazo.isEmpty())
                .collect(Collectors.toList());

        if (!messageSeparatedByZerosAndOnes.isEmpty() && messageSeparatedByZerosAndOnes.get(0).contains("0")) {
            // REMOVER LA PAUSA INICIAL
            messageSeparatedByZerosAndOnes.remove(0);
        }

        return messageSeparatedByZerosAndOnes;
    }

    private static List<String> filterBySubstring(List<String> list, String substring) {
        return list.stream()
                .filter(element -> element.contains(substring))
                .collect(Collectors.toList());
    }

    private static List<Integer> countDigitsPerPosition(List<String> digitList, char digit) {
        int maxLength = digitList.size();
        int[] countArray = new int[maxLength];

        for (int i = 0; i < digitList.size(); i++) {
            countArray[i] = digitList.get(i).length();
        }
        return List.of(Arrays.stream(countArray).boxed().toArray(Integer[]::new));
    }


    public static String translate(String bitSequence) {
        List<String> listSeparatedByZeros = separateByZeros(bitSequence);
        List<String> soloParis = listSeparatedByZeros.subList(0, Math.min(listSeparatedByZeros.size(), 84));

        calibrarTraductor(soloParis);

        listSeparatedByZeros.subList(0, 84).clear();

        StringBuilder morseResult = new StringBuilder();

        for (String subcadena : listSeparatedByZeros) {  //TODO: VALIDAR QUE INGRESO ALGO DESPUES DE PARIS
            if (subcadena.contains("0")) {
                morseResult.append(AmountSpaces(subcadena));
            } else if (subcadena.contains("1")) {
                morseResult.append(PuntoOraya(subcadena));
            }
        }
        return morseResult.toString();
    }
}

