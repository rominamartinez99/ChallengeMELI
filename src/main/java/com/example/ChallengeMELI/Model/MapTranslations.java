package com.example.ChallengeMELI.Model;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapTranslations {
    private static final Map<Character,String> human2MorseMap = new LinkedHashMap<>();
    private static final Map<String,Character> morse2HumanMap = new LinkedHashMap<>();

    public MapTranslations(){
        human2MorseMap.put('A', ".-");
        human2MorseMap.put('B', "-...");
        human2MorseMap.put('C', "-.-.");
        human2MorseMap.put('D', "-..");
        human2MorseMap.put('E', ".");
        human2MorseMap.put('F', "..-.");
        human2MorseMap.put('G', "--.");
        human2MorseMap.put('H', "....");
        human2MorseMap.put('I', "..");
        human2MorseMap.put('J', ".---");
        human2MorseMap.put('K', "-.-");
        human2MorseMap.put('L', ".-..");
        human2MorseMap.put('M', "--");
        human2MorseMap.put('N', "-.");
        human2MorseMap.put('O', "---");
        human2MorseMap.put('P', ".--.");
        human2MorseMap.put('Q', "--.-");
        human2MorseMap.put('R', ".-.");
        human2MorseMap.put('S', "...");
        human2MorseMap.put('T', "-");
        human2MorseMap.put('U', "..-");
        human2MorseMap.put('V', "...-");
        human2MorseMap.put('W', ".--");
        human2MorseMap.put('X', "-..-");
        human2MorseMap.put('Y', "-.--");
        human2MorseMap.put('Z', "--..");
        human2MorseMap.put('0', "-----");
        human2MorseMap.put('1', ".----");
        human2MorseMap.put('2', "..---");
        human2MorseMap.put('3', "...--");
        human2MorseMap.put('4', "....-");
        human2MorseMap.put('5', ".....");
        human2MorseMap.put('6', "-....");
        human2MorseMap.put('7', "--...");
        human2MorseMap.put('8', "---..");
        human2MorseMap.put('9', "----.");
        human2MorseMap.put(' ', "  ");

        for (Map.Entry<Character, String> entry : human2MorseMap.entrySet()) {
            morse2HumanMap.put(entry.getValue(), entry.getKey());
        }
    }

    public static Map<Character, String> getMapHuman2Morse() {
        return new LinkedHashMap<>(human2MorseMap);
    }

    public static Map<String, Character> getMapMorse2Human() {
        return new LinkedHashMap<>(morse2HumanMap);
    }
}
